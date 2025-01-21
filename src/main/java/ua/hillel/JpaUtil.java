package ua.hillel;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public final class JpaUtil {
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY =
            Persistence.createEntityManagerFactory("hillel-persistence-unit");

    private JpaUtil() {}

    public static EntityManager getEntityManager() {
        return ENTITY_MANAGER_FACTORY.createEntityManager();
    }

    public static void closeEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        if (entityManagerFactory.isOpen()){
            entityManagerFactory.close();
        }
    }
}
