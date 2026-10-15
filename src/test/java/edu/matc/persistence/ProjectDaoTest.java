package edu.matc.persistence;

import edu.matc.entity.Project;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        Project project = createTestProject("Enterprise Java");
        Project retrievedProject = projectDao.getById(project.getId());

        assertEquals(project, retrievedProject);
    }

    /**
     * Verifies a project can be inserted.
     */
    @Test
    void insertSuccess() {
        Project project = new Project("Portfolio");
        int id = projectDao.insert(project);

        project.setId(id);
        assertEquals(project, projectDao.getById(id));
    }

    /**
     * Verifies a project can be updated.
     */
    @Test
    void updateSuccess() {
        Project project = createTestProject("School");
        project.setName("College");
        projectDao.update(project);

        assertEquals(project, projectDao.getById(project.getId()));
    }

    /**
     * Verifies a project can be deleted.
     */
    @Test
    void deleteSuccess() {
        Project project = createTestProject("Temporary");
        projectDao.delete(project);

        assertNull(projectDao.getById(project.getId()));
    }

    /**
     * Verifies all projects can be returned.
     */
    @Test
    void getAllSuccess() {
        Project firstProject = createTestProject("School");
        Project secondProject = createTestProject("Work");

        List<Project> projects = projectDao.getAll();

        assertEquals(2, projects.size());
        assertTrue(projects.contains(firstProject));
        assertTrue(projects.contains(secondProject));
    }
}
