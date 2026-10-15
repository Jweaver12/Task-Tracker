package edu.matc.persistence;

import edu.matc.entity.Category;
import edu.matc.entity.Project;
import edu.matc.entity.Task;
import edu.matc.entity.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        Task task = createTestTask("Complete checkpoint", false);
        Task retrievedTask = taskDao.getById(task.getId());

        assertEquals(task, retrievedTask);
        assertEquals(task.getProject(), retrievedTask.getProject());
        assertEquals(task.getCategory(), retrievedTask.getCategory());
        assertEquals(task.getUser(), retrievedTask.getUser());
    }

    /**
     * Verifies a task can be inserted.
     */
    @Test
    void insertSuccess() {
        Project project = createTestProject("Enterprise Java");
        Category category = createTestCategory("Coursework");
        User user = createTestUser("jweaver");

        Task task = new Task("Create DAO tests", "Test full CRUD",
                LocalDate.of(2026, 10, 18), false, project, category);
        task.setUser(user);

        int id = taskDao.insert(task);
        task.setId(id);

        assertEquals(task, taskDao.getById(id));
    }

    /**
     * Verifies a task can be updated.
     */
    @Test
    void updateSuccess() {
        Task task = createTestTask("Finish project", false);
        task.setDescription("Finish Checkpoint 2 work");
        task.setCompleted(true);
        taskDao.update(task);

        assertEquals(task, taskDao.getById(task.getId()));
    }

    /**
     * Verifies a task can be deleted.
     */
    @Test
    void deleteSuccess() {
        Task task = createTestTask("Temporary task", false);
        taskDao.delete(task);

        assertNull(taskDao.getById(task.getId()));
    }

    /**
     * Verifies all tasks can be returned.
     */
    @Test
    void getAllSuccess() {
        Task firstTask = createTestTask("First task", false);
        Task secondTask = createTestTask("Second task", true);

        List<Task> tasks = taskDao.getAll();

        assertEquals(2, tasks.size());
        assertTrue(tasks.contains(firstTask));
        assertTrue(tasks.contains(secondTask));
    }

    /**
     * Creates and inserts a task with a user, project, and category.
     *
     * @param name task name
     * @param completed completion status
     * @return inserted task
     */
    private Task createTestTask(String name, boolean completed) {
        Project project = createTestProject(name + " Project");
        Category category = createTestCategory(name + " Category");
        User user = createTestUser(name + " User");

        Task task = new Task(name, "Test task",
                LocalDate.of(2026, 10, 18), completed, project, category);
        task.setUser(user);

        int id = taskDao.insert(task);
        task.setId(id);
        return task;
    }
}