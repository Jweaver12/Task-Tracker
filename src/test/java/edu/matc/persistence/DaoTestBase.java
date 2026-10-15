package edu.matc.persistence;

import edu.matc.entity.Category;
import edu.matc.entity.Project;
import edu.matc.entity.Task;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.BeforeEach;

/**
 * Provides common setup used by DAO tests.
 *
 * @author JaQuan Weaver
 */
public abstract class DaoTestBase {

    protected GenericDao<Project> projectDao;
    protected GenericDao<Category> categoryDao;
    protected GenericDao<Task> taskDao;

    /**
     * Creates DAO objects and clears test data before each test.
     */
    @BeforeEach
    void setUpDaos() {
        projectDao = new GenericDao<>(Project.class);
        categoryDao = new GenericDao<>(Category.class);
        taskDao = new GenericDao<>(Task.class);
        clearDatabase();
    }

    /**
     * Removes Task Tracker test data in foreign-key-safe order.
     */
    protected void clearDatabase() {
        Transaction transaction = null;

        try (Session session = SessionFactoryProvider.getSessionFactory()
                .openSession()) {
            transaction = session.beginTransaction();
            session.createMutationQuery("DELETE FROM Task").executeUpdate();
            session.createMutationQuery("DELETE FROM Project").executeUpdate();
            session.createMutationQuery("DELETE FROM Category").executeUpdate();
            transaction.commit();
        } catch (RuntimeException exception) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw exception;
        }
    }

    /**
     * Creates and inserts a project for a test.
     *
     * @param name project name
     * @return inserted project
     */
    protected Project createTestProject(String name) {
        Project project = new Project(name);
        int id = projectDao.insert(project);
        project.setId(id);
        return project;
    }

    /**
     * Creates and inserts a category for a test.
     *
     * @param name category name
     * @return inserted category
     */
    protected Category createTestCategory(String name) {
        Category category = new Category(name);
        int id = categoryDao.insert(category);
        category.setId(id);
        return category;
    }
}
