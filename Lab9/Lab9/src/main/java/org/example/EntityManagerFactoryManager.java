package org.example;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFactoryManager {
    private static EntityManagerFactoryManager instance = null;
    private EntityManagerFactory entityManagerFactory = null;

    private EntityManagerFactoryManager() {
    }

    public static EntityManagerFactoryManager getInstance() {
        if (instance == null) {
            synchronized (EntityManagerFactoryManager.class) {
                if (instance == null) {
                    instance = new EntityManagerFactoryManager();
                }
            }
        }
        return instance;
    }

    public EntityManagerFactory getEntityManagerFactory() {
        if (entityManagerFactory == null) {
            entityManagerFactory = Persistence.createEntityManagerFactory("PersistenceUnitName");
        }
        return entityManagerFactory;
    }

    public void closeEntityManagerFactory() {
        if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
            entityManagerFactory = null;
        }
    }
}
