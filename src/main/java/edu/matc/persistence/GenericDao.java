package edu.matc.persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Provides common Hibernate create, read, update, and delete operations.
 * This follows the generic DAO approach introduced in the course.
 *
 * @param <T> entity type handled by the DAO
 * @author JaQuan Weaver
 */
public class GenericDao<T> {

    private final Logger logger = LogManager.getLogger(this.getClass());
    private final Class<T> type;
    private final SessionFactory sessionFactory;

    /**
     * Instantiates a new Generic DAO.
     *
     * @param type entity class handled by this DAO
     */
    public GenericDao(Class<T> type) {
        this.type = type;
        sessionFactory = SessionFactoryProvider.getSessionFactory();
    }

    /**
     * Gets an entity by id.
     *
     * @param id entity id
     * @return matching entity or null
     */
    public T getById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(type, id);
        }
    }

    /**
     * Updates an entity.
     *
     * @param entity entity to update
     */
    public void update(T entity) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(entity);
            transaction.commit();
            logger.debug("Updated {}", type.getSimpleName());
        }
    }

    /**
     * Inserts an entity.
     *
     * @param entity entity to insert
     * @return generated entity id
     */
    public int insert(T entity) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            int id = (Integer) session.save(entity);
            transaction.commit();
            logger.debug("Inserted {} with id {}", type.getSimpleName(), id);
            return id;
        }
    }

    /**
     * Deletes an entity.
     *
     * @param entity entity to delete
     */
    public void delete(T entity) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            T managedEntity = session.merge(entity);
            session.remove(managedEntity);
            transaction.commit();
            logger.debug("Deleted {}", type.getSimpleName());
        }
    }

    /**
     * Gets all entities of this type.
     *
     * @return all matching entities
     */
    public List<T> getAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createSelectionQuery(
                    "FROM " + type.getSimpleName(), type).getResultList();
        }
    }
}
