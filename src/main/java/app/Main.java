package app;

import app.config.HibernateConfig;
import app.daos.PersonDAO;
import app.entities.Person;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {

        Person p1 = Person.builder()
                .name("Sofus")
                .age(20)
                .build();

        // Svarer til en fabrik der kan lave en connectionPool
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();

        PersonDAO personDAO = new PersonDAO(emf);
        // Dette er vores connectionPool (forbindelse)
        EntityManager em = emf.createEntityManager();


        personDAO.createPerson(p1);

        em.close();
        emf.close();

    }
}