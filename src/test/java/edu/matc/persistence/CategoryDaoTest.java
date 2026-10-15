package edu.matc.persistence;

import edu.matc.entity.Category;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Tests Category persistence using the generic DAO.
 *
 * @author JaQuan Weaver
 */
class CategoryDaoTest extends DaoTestBase {

    /**
     * Verifies a category can be retrieved by id.
     */
    @Test
    void getByIdSuccess() {
        Category retrievedCategory = categoryDao.getById(1);

        assertNotNull(retrievedCategory);
        assertEquals("Homework", retrievedCategory.getName());
    }

    /**
     * Verifies a category can be inserted.
     */
    @Test
    void insertSuccess() {
        Category category = new Category("Personal");

        int id = categoryDao.insert(category);

        assertNotEquals(0, id);

        Category insertedCategory = categoryDao.getById(id);

        assertNotNull(insertedCategory);
        assertEquals("Personal", insertedCategory.getName());
    }

    /**
     * Verifies a category can be updated.
     */
    @Test
    void updateSuccess() {
        Category categoryToUpdate = categoryDao.getById(2);

        categoryToUpdate.setName("Coursework");
        categoryDao.update(categoryToUpdate);

        Category categoryAfterUpdate = categoryDao.getById(2);

        assertEquals("Coursework", categoryAfterUpdate.getName());
    }

    /**
     * Verifies a category can be deleted.
     */
    @Test
    void deleteSuccess() {
        Category categoryToDelete = categoryDao.getById(3);

        categoryDao.delete(categoryToDelete);

        assertNull(categoryDao.getById(3));
    }

    /**
     * Verifies all categories can be returned.
     */
    @Test
    void getAllSuccess() {
        List<Category> categories = categoryDao.getAll();

        assertEquals(3, categories.size());
    }
}