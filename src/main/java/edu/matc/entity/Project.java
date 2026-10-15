package edu.matc.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Represents a project used to group related tasks.
 *
 * @author JaQuan Weaver
 */
@Entity
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "project")
    private Set<Task> tasks = new HashSet<>();

    /**
     * Instantiates a new Project.
     */
    public Project() {
    }

    /**
     * Instantiates a new Project.
     *
     * @param name project name
     */
    public Project(String name) {
        this.name = name;
    }

    /**
     * Gets id.
     *
     * @return project id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id project id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets name.
     *
     * @return project name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name project name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets tasks.
     *
     * @return project tasks
     */
    public Set<Task> getTasks() {
        return tasks;
    }

    /**
     * Sets tasks.
     *
     * @param tasks project tasks
     */
    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to this project.
     *
     * @param task task to add
     */
    public void addTask(Task task) {
        tasks.add(task);
        task.setProject(this);
    }

    /**
     * Compares this entity with another entity.
     *
     * @param object object to compare
     * @return true when the entity values match
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Project)) {
            return false;
        }
        Project project = (Project) object;
        return Objects.equals(name, project.name);
    }

    /**
     * Returns the hash code for this entity.
     *
     * @return entity hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    /**
     * Returns a string representation of this entity.
     *
     * @return entity information
     */
    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}