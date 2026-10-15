package edu.matc.persistence;

import edu.matc.entity.User;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests User persistence using the generic DAO.
 *
 * @author JaQuan Weaver
 */
class UserDaoTest extends DaoTestBase {

    /**
     * Verifies a user can be retrieved by id.
     */
    @Test
    void getByIdSuccess() {
        User user = createTestUser("jweaver");

        User retrievedUser = userDao.getById(user.getId());

        assertEquals(user, retrievedUser);
    }

    /**
     * Verifies a user can be inserted.
     */
    @Test
    void insertSuccess() {
        User user = new User("testuser");

        int id = userDao.insert(user);
        user.setId(id);

        assertEquals(user, userDao.getById(id));
    }

    /**
     * Verifies a user can be updated.
     */
    @Test
    void updateSuccess() {
        User user = createTestUser("oldusername");
        user.setUsername("newusername");

        userDao.update(user);

        assertEquals(user, userDao.getById(user.getId()));
    }

    /**
     * Verifies a user can be deleted.
     */
    @Test
    void deleteSuccess() {
        User user = createTestUser("deleteuser");

        userDao.delete(user);

        assertNull(userDao.getById(user.getId()));
    }

    /**
     * Verifies all users can be returned.
     */
    @Test
    void getAllSuccess() {
        User firstUser = createTestUser("firstuser");
        User secondUser = createTestUser("seconduser");

        List<User> users = userDao.getAll();

        assertEquals(2, users.size());
        assertTrue(users.contains(firstUser));
        assertTrue(users.contains(secondUser));
    }
}