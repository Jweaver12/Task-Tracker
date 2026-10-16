package edu.matc.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.matc.auth.CognitoJWTParser;
import edu.matc.auth.CognitoTokenHeader;
import edu.matc.auth.Keys;
import edu.matc.auth.KeysItem;
import edu.matc.auth.TokenResponse;
import edu.matc.util.PropertiesLoader;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * Handles authentication responses from AWS Cognito.
 *
 * @author JaQuan Weaver
 */
@WebServlet("/auth")
public class Auth extends HttpServlet implements PropertiesLoader {

    private Properties properties;
    private String clientId;
    private String clientSecret;
    private String oauthUrl;
    private String redirectUrl;
    private String region;
    private String poolId;
    private Keys jwks;

    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Loads Cognito properties and keys when the servlet starts.
     *
     * @throws ServletException if the servlet cannot initialize
     */
    @Override
    public void init() throws ServletException {
        super.init();
        loadCognitoProperties();
        loadKey();
    }

    /**
     * Receives the authorization code from Cognito and exchanges it
     * for user information.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if the request cannot be processed
     * @throws IOException if an input or output error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String authCode = request.getParameter("code");

        if (authCode != null) {
            HttpRequest authRequest = buildAuthRequest(authCode);

            try {
                TokenResponse tokenResponse = getToken(authRequest);
                String userName = validate(tokenResponse);

                request.setAttribute("userName", userName);
                request.getSession().setAttribute("userName", userName);

            } catch (IOException e) {
                logger.error(
                        "Error getting or validating the token",
                        e);
            } catch (InterruptedException e) {
                logger.error(
                        "Error getting token from Cognito",
                        e);

                Thread.currentThread().interrupt();
            }
        }

        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/index.jsp");

        dispatcher.forward(request, response);
    }

    /**
     * Sends a request to Cognito for authentication tokens.
     *
     * @param authRequest request to Cognito
     * @return Cognito token response
     * @throws IOException if the request cannot be completed
     * @throws InterruptedException if the request is interrupted
     */
    private TokenResponse getToken(HttpRequest authRequest)
            throws IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();

        HttpResponse<String> response = client.send(
                authRequest,
                HttpResponse.BodyHandlers.ofString());

        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(
                response.body(),
                TokenResponse.class);
    }

    /**
     * Validates the Cognito token and retrieves the username.
     *
     * @param tokenResponse Cognito token response
     * @return authenticated username
     * @throws IOException if token data cannot be read
     */
    private String validate(TokenResponse tokenResponse)
            throws IOException {

        ObjectMapper mapper = new ObjectMapper();

        CognitoTokenHeader tokenHeader = mapper.readValue(
                CognitoJWTParser
                        .getHeader(tokenResponse.getIdToken())
                        .toString(),
                CognitoTokenHeader.class);

        String keyId = tokenHeader.getKid();

        KeysItem matchingKey = null;

        for (KeysItem key : jwks.getKeys()) {
            if (key.getKid().equals(keyId)) {
                matchingKey = key;
                break;
            }
        }

        if (matchingKey == null) {
            throw new IOException("Matching Cognito key not found");
        }

        BigInteger modulus = new BigInteger(
                1,
                org.apache.commons.codec.binary.Base64.decodeBase64(
                        matchingKey.getN()));

        BigInteger exponent = new BigInteger(
                1,
                org.apache.commons.codec.binary.Base64.decodeBase64(
                        matchingKey.getE()));

        PublicKey publicKey = null;

        try {
            publicKey = KeyFactory
                    .getInstance("RSA")
                    .generatePublic(
                            new RSAPublicKeySpec(modulus, exponent));

        } catch (InvalidKeySpecException e) {
            logger.error("Invalid key", e);
        } catch (NoSuchAlgorithmException e) {
            logger.error("Algorithm error", e);
        }

        Algorithm algorithm =
                Algorithm.RSA256((RSAPublicKey) publicKey, null);

        String issuer = String.format(
                "https://cognito-idp.%s.amazonaws.com/%s",
                region,
                poolId);

        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer(issuer)
                .withClaim("token_use", "id")
                .build();

        DecodedJWT jwt =
                verifier.verify(tokenResponse.getIdToken());

        return jwt.getClaim("email").asString();
    }

    /**
     * Builds the request used to exchange the Cognito authorization
     * code for authentication tokens.
     *
     * @param authCode authorization code returned by Cognito
     * @return request to the Cognito token endpoint
     */
    private HttpRequest buildAuthRequest(String authCode) {

        String keys = clientId + ":" + clientSecret;

        HashMap<String, String> parameters = new HashMap<>();

        parameters.put("grant_type", "authorization_code");
        parameters.put("client_id", clientId);
        parameters.put("code", authCode);
        parameters.put("redirect_uri", redirectUrl);

        String form = parameters.keySet()
                .stream()
                .map(key -> key + "="
                        + URLEncoder.encode(
                        parameters.get(key),
                        StandardCharsets.UTF_8))
                .collect(Collectors.joining("&"));

        String encoding = Base64.getEncoder()
                .encodeToString(keys.getBytes());

        return HttpRequest.newBuilder()
                .uri(URI.create(oauthUrl))
                .headers(
                        "Content-Type",
                        "application/x-www-form-urlencoded",
                        "Authorization",
                        "Basic " + encoding)
                .POST(HttpRequest.BodyPublishers.ofString(form))
                .build();
    }

    /**
     * Loads the JSON Web Key Set for the Cognito user pool.
     */
    private void loadKey() {

        ObjectMapper mapper = new ObjectMapper();

        try {
            URL jwksUrl = new URL(
                    String.format(
                            "https://cognito-idp.%s.amazonaws.com/%s/"
                                    + ".well-known/jwks.json",
                            region,
                            poolId));

            File jwksFile = new File("jwks.json");

            FileUtils.copyURLToFile(jwksUrl, jwksFile);

            jwks = mapper.readValue(jwksFile, Keys.class);

        } catch (IOException e) {
            logger.error("Cannot load Cognito keys", e);
        }
    }

    /**
     * Loads the local Cognito configuration.
     */
    private void loadCognitoProperties() {

        try {
            properties = loadProperties("/cognito.properties");

            clientId = properties.getProperty("client.id");
            clientSecret = properties.getProperty("client.secret");
            oauthUrl = properties.getProperty("oauthURL");
            redirectUrl = properties.getProperty("redirectURL");
            region = properties.getProperty("region");
            poolId = properties.getProperty("poolId");

        } catch (IOException e) {
            logger.error("Cannot load Cognito properties", e);
        } catch (Exception e) {
            logger.error("Error loading Cognito properties", e);
        }
    }
}