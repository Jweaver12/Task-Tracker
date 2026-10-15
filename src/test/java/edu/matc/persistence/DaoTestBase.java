package edu.matc.persistence;

import edu.matc.entity.Category;
import edu.matc.entity.Project;
import edu.matc.entity.Task;
import edu.matc.entity.User;
import edu.matc.testUtils.Database;
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
    protected GenericDao<User> userDao;

    /**
     * Resets test data and creates DAO objects before each test.
     */
    @BeforeEach
    void setUpDaos() {
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");

        projectDao = new GenericDao<>(Project.class);
        categoryDao = new GenericDao<>(Category.class);
        taskDao = new GenericDao<>(Task.class);
        userDao = new GenericDao<>(User.class);
    }
}