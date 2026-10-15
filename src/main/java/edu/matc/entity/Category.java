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
 * Represents a category used to organize tasks by type.
 *
 * @author JaQuan Weaver
 */
@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "category")
    private Set<Task> tasks = new HashSet<>();

    /**
     * Instantiates a new Category.
     */
    public Category() {
    }

    /**
     * Instantiates a new Category.
     *
     * @param name category name
     */
    public Category(String name) {
        this.name = name;
    }

    /**
     * Gets id.
     *
     * @return category id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id category id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets name.
     *
     * @return category name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name category name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets tasks.
     *
     * @return category tasks
     */
    public Set<Task> getTasks() {
        return tasks;
    }

    /**
     * Sets tasks.
     *
     * @param tasks category tasks
     */
    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to this category.
     *
     * @param task task to add
     */
    public void addTask(Task task) {
        tasks.add(task);
        task.setCategory(this);
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
        if (!(object instanceof Category)) {
            return false;
        }
        Category category = (Category) object;
        return Objects.equals(name, category.name);
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
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}