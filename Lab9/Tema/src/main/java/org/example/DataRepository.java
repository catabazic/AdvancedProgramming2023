package org.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.io.Serializable;
import java.util.List;

public abstract class DataRepository  <T> implements Serializable {
    private final Class<T> entityClass;
    private final EntityManager entityManager;
    private final EntityManagerFactory entityManagerFactory;

    public DataRepository(Class<T> entityClass) {
        this.entityClass = entityClass;
        entityManagerFactory = EntityManagerFactoryManager.getEntityManagerFactory("Persistence");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public void persist(T entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
    }

    public T findById(int id) {
        return entityManager.find(entityClass, id);
    }

    public List<T> findByName(String name) {
        return entityManager.createNamedQuery(
                        entityClass.getSimpleName() + ".findByName", entityClass).
                setParameter("name", "%" + name + "%").
                getResultList();
    }

}
