package edu.matc.persistence;

import edu.matc.entity.User;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

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
        User retrievedUser = userDao.getById(1);

        assertNotNull(retrievedUser);
        assertEquals("jweaver", retrievedUser.getUsername());
    }

    /**
     * Verifies a user can be inserted.
     */
    @Test
    void insertSuccess() {
        User user = new User("newuser");

        int id = userDao.insert(user);

        assertNotEquals(0, id);

        User insertedUser = userDao.getById(id);

        assertNotNull(insertedUser);
        assertEquals("newuser", insertedUser.getUsername());
    }

    /**
     * Verifies a user can be updated.
     */
    @Test
    void updateSuccess() {
        User userToUpdate = userDao.getById(2);

        userToUpdate.setUsername("updateduser");
        userDao.update(userToUpdate);

        User userAfterUpdate = userDao.getById(2);

        assertEquals("updateduser", userAfterUpdate.getUsername());
    }

    /**
     * Verifies a user can be deleted.
     */
    @Test
    void deleteSuccess() {
        User userToDelete = userDao.getById(3);

        userDao.delete(userToDelete);

        assertNull(userDao.getById(3));
    }

    /**
     * Verifies all users can be returned.
     */
    @Test
    void getAllSuccess() {
        List<User> users = userDao.getAll();

        assertEquals(3, users.size());
    }
}