package edu.matc.persistence;

import edu.matc.entity.Category;
import edu.matc.entity.Project;
import edu.matc.entity.Task;
import edu.matc.entity.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Tests Task persistence and entity relationships using the generic DAO.
 *
 * @author JaQuan Weaver
 */
class TaskDaoTest extends DaoTestBase {

    /**
     * Verifies a task can be retrieved by id with its relationships.
     */
    @Test
    void getByIdSuccess() {
        Task retrievedTask = taskDao.getById(1);

        assertNotNull(retrievedTask);
        assertEquals("Java Assignment", retrievedTask.getName());
        assertEquals("School", retrievedTask.getProject().getName());
        assertEquals("Homework", retrievedTask.getCategory().getName());
        assertEquals("jweaver", retrievedTask.getUser().getUsername());
    }

    /**
     * Verifies a task can be inserted.
     */
    @Test
    void insertSuccess() {
        Project project = projectDao.getById(1);
        Category category = categoryDao.getById(1);
        User user = userDao.getById(1);

        Task task = new Task(
                "Create DAO tests",
                "Test full CRUD",
                LocalDate.of(2026, 10, 18),
                false,
                project,
                category);

        task.setUser(user);

        int id = taskDao.insert(task);

        assertNotEquals(0, id);

        Task insertedTask = taskDao.getById(id);

        assertNotNull(insertedTask);
        assertEquals("Create DAO tests", insertedTask.getName());
        assertEquals(project, insertedTask.getProject());
        assertEquals(category, insertedTask.getCategory());
        assertEquals(user, insertedTask.getUser());
    }

    /**
     * Verifies a task can be updated.
     */
    @Test
    void updateSuccess() {
        Task taskToUpdate = taskDao.getById(1);

        taskToUpdate.setDescription("Finish Checkpoint 2 work");
        taskToUpdate.setCompleted(true);
        taskDao.update(taskToUpdate);

        Task taskAfterUpdate = taskDao.getById(1);

        assertEquals(
                "Finish Checkpoint 2 work",
                taskAfterUpdate.getDescription());
        assertEquals(true, taskAfterUpdate.isCompleted());
    }

    /**
     * Verifies a task can be deleted.
     */
    @Test
    void deleteSuccess() {
        Task taskToDelete = taskDao.getById(2);

        taskDao.delete(taskToDelete);

        assertNull(taskDao.getById(2));
    }

    /**
     * Verifies all tasks can be returned.
     */
    @Test
    void getAllSuccess() {
        List<Task> tasks = taskDao.getAll();

        assertEquals(2, tasks.size());
    }
}