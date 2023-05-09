package org.example;

import javax.persistence.Persistence;

public class EntityManagerFactory {
    private static javax.persistence.EntityManagerFactory entityManagerFactory = null;

    private EntityManagerFactory() {
    }


    public static javax.persistence.EntityManagerFactory getEntityManagerFactory(String unitName) {
        if (entityManagerFactory == null) {
            entityManagerFactory = Persistence.createEntityManagerFactory(unitName);
        }
        return entityManagerFactory;
    }

    public static void closeEntityManagerFactory() {
        if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
            entityManagerFactory = null;
        }
    }
}
