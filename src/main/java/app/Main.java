package app;

import app.config.HibernateConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        // Svarer til en fabrik der kan lave en connectionPool
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();

        // Dette er vores connectionPool (forbindelse)
        EntityManager em = emf.createEntityManager();


        em.close();
        emf.close();

    }
}