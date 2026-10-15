package edu.matc.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Represents a task that belongs to a project and category.
 *
 * @author JaQuan Weaver
 */
@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @Column(name = "completed")
    private boolean completed;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    /**
     * Instantiates a new Task.
     */
    public Task() {
    }

    /**
     * Instantiates a new Task.
     *
     * @param name task name
     * @param description task description
     * @param dueDate task due date
     * @param completed task completion status
     * @param project task project
     * @param category task category
     */
    public Task(String name, String description, LocalDate dueDate,
                boolean completed, Project project, Category category) {
        this.name = name;
        this.description = description;
        this.dueDate = dueDate;
        this.completed = completed;
        this.project = project;
        this.category = category;
    }

    /**
     * Gets id.
     *
     * @return task id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id task id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets name.
     *
     * @return task name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name task name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets description.
     *
     * @return task description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description task description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets due date.
     *
     * @return task due date
     */
    public LocalDate getDueDate() {
        return dueDate;
    }

    /**
     * Sets due date.
     *
     * @param dueDate task due date
     */
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * Returns whether the task is complete.
     *
     * @return true when complete
     */
    public boolean isCompleted() {
        return completed;
    }

    /**
     * Sets completion status.
     *
     * @param completed task completion status
     */
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    /**
     * Gets project.
     *
     * @return task project
     */
    public Project getProject() {
        return project;
    }

    /**
     * Sets project.
     *
     * @param project task project
     */
    public void setProject(Project project) {
        this.project = project;
    }

    /**
     * Gets category.
     *
     * @return task category
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Sets category.
     *
     * @param category task category
     */
    public void setCategory(Category category) {
        this.category = category;
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
        if (!(object instanceof Task)) {
            return false;
        }
        Task task = (Task) object;
        return completed == task.completed
                && Objects.equals(name, task.name)
                && Objects.equals(description, task.description)
                && Objects.equals(dueDate, task.dueDate)
                && Objects.equals(project, task.project)
                && Objects.equals(category, task.category);
    }

    /**
     * Returns the hash code for this entity.
     *
     * @return entity hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, description, dueDate, completed,
                project, category);
    }

    /**
     * Returns a string representation of this entity.
     *
     * @return entity information
     */
    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", dueDate=" + dueDate +
                ", completed=" + completed +
                ", project=" + project +
                ", category=" + category +
                '}';
    }
}
