package edu.matc.persistence;

import edu.matc.entity.Project;
import edu.matc.entity.User;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Tests Project persistence using the generic DAO.
 *
 * @author JaQuan Weaver
 */
class ProjectDaoTest extends DaoTestBase {

    /**
     * Verifies a project can be retrieved by id.
     */
    @Test
    void getByIdSuccess() {
        Project retrievedProject = projectDao.getById(1);

        assertNotNull(retrievedProject);
        assertEquals("School", retrievedProject.getName());
        assertEquals(
                "jweaver",
                retrievedProject.getUser().getUsername());
    }

    /**
     * Verifies a project can be inserted.
     */
    @Test
    void insertSuccess() {
        User user = userDao.getById(1);

        Project project = new Project("Portfolio");
        project.setUser(user);

        int id = projectDao.insert(project);

        assertNotEquals(0, id);

        Project insertedProject = projectDao.getById(id);

        assertNotNull(insertedProject);
        assertEquals("Portfolio", insertedProject.getName());
        assertEquals(user, insertedProject.getUser());
    }

    /**
     * Verifies a project can be updated.
     */
    @Test
    void updateSuccess() {
        Project projectToUpdate = projectDao.getById(2);

        projectToUpdate.setName("College");
        projectDao.update(projectToUpdate);

        Project projectAfterUpdate = projectDao.getById(2);

        assertEquals("College", projectAfterUpdate.getName());
    }

    /**
     * Verifies deleting a project also deletes its tasks.
     */
    @Test
    void deleteSuccess() {
        Project projectToDelete = projectDao.getById(2);

        projectDao.delete(projectToDelete);

        assertNull(projectDao.getById(2));
        assertNull(taskDao.getById(2));
        assertNotNull(userDao.getById(2));
    }

    /**
     * Verifies all projects can be returned.
     */
    @Test
    void getAllSuccess() {
        List<Project> projects = projectDao.getAll();

        assertEquals(3, projects.size());
    }
}