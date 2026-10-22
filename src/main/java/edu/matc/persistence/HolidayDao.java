package edu.matc.persistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.matc.entity.Holiday;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;

/**
 * Calls the public holiday REST service.
 *
 * @author JaQuan Weaver
 */
public class HolidayDao {

    /**
     * Gets United States public holidays for a year.
     *
     * @param year year to retrieve
     * @return list of holidays
     * @throws IOException if the response cannot be read
     * @throws InterruptedException if the request is interrupted
     */
    public List<Holiday> getHolidays(int year)
            throws IOException, InterruptedException {

        String url =
                "https://date.nager.at/api/v3/PublicHolidays/"
                        + year + "/US";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpClient client = HttpClient.newHttpClient();

        HttpResponse<String> response = client.send(
                request,
                HttpResponse.BodyHandlers.ofString());

        ObjectMapper mapper = new ObjectMapper();

        Holiday[] holidays = mapper.readValue(
                response.body(),
                Holiday[].class);

        return Arrays.asList(holidays);
    }
}