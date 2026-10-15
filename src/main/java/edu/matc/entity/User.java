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
 * Represents a user who owns tasks in the Task Tracker.
 *
 * @author JaQuan Weaver
 */
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username")
    private String username;

    @OneToMany(mappedBy = "user")
    private Set<Task> tasks = new HashSet<>();

    /**
     * Instantiates a new User.
     */
    public User() {
    }

    /**
     * Instantiates a new User.
     *
     * @param username user's username
     */
    public User(String username) {
        this.username = username;
    }

    /**
     * Gets id.
     *
     * @return user id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id user id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets username.
     *
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets username.
     *
     * @param username username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets tasks.
     *
     * @return user's tasks
     */
    public Set<Task> getTasks() {
        return tasks;
    }

    /**
     * Sets tasks.
     *
     * @param tasks user's tasks
     */
    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to this user.
     *
     * @param task task to add
     */
    public void addTask(Task task) {
        tasks.add(task);
        task.setUser(this);
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
        if (!(object instanceof User)) {
            return false;
        }
        User user = (User) object;
        return Objects.equals(username, user.username);
    }

    /**
     * Returns the hash code for this entity.
     *
     * @return entity hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

    /**
     * Returns a string representation of this entity.
     *
     * @return entity information
     */
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }
}