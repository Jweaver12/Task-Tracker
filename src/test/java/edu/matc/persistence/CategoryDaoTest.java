package edu.matc.persistence;

import edu.matc.entity.Category;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        Category category = createTestCategory("Coursework");
        Category retrievedCategory = categoryDao.getById(category.getId());

        assertEquals(category, retrievedCategory);
    }

    /**
     * Verifies a category can be inserted.
     */
    @Test
    void insertSuccess() {
        Category category = new Category("Personal");
        int id = categoryDao.insert(category);

        category.setId(id);
        assertEquals(category, categoryDao.getById(id));
    }

    /**
     * Verifies a category can be updated.
     */
    @Test
    void updateSuccess() {
        Category category = createTestCategory("School");
        category.setName("Coursework");
        categoryDao.update(category);

        assertEquals(category, categoryDao.getById(category.getId()));
    }

    /**
     * Verifies a category can be deleted.
     */
    @Test
    void deleteSuccess() {
        Category category = createTestCategory("Temporary");
        categoryDao.delete(category);

        assertNull(categoryDao.getById(category.getId()));
    }

    /**
     * Verifies all categories can be returned.
     */
    @Test
    void getAllSuccess() {
        Category firstCategory = createTestCategory("Coursework");
        Category secondCategory = createTestCategory("Personal");

        List<Category> categories = categoryDao.getAll();

        assertEquals(2, categories.size());
        assertTrue(categories.contains(firstCategory));
        assertTrue(categories.contains(secondCategory));
    }
}
