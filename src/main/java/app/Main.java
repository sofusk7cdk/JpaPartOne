package app;

import app.config.HibernateConfig;
import app.daos.CourseDAO;
import app.daos.PersonDAO;
import app.daos.StudentDAO;
import app.entities.Course;
import app.entities.Person;
import app.entities.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {

        Person p1 = Person.builder()
                .name("Sofus")
                .age(20)
                .build();

        Student s1 = Student.builder()
                .name("Sofus")
                .phoneNumber("+45 34023388")
                .email("sofus@kkkkkkkk.dk")
                .address("Vejen")
                .status(3)
                .dayOfBirth(LocalDate.of(2014,8,9))
                .build();

        Course c1 = Course.builder()
                .name("Software")
                .teacherName("Jon")
                .semester(3)
                .classroom(7)
                .build();

        // Svarer til en fabrik der kan lave en connectionPool
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();

        // Instansier DAOS
        PersonDAO personDAO = new PersonDAO(emf);
        StudentDAO studentDAO = new StudentDAO(emf);
        CourseDAO courseDAO = new CourseDAO(emf);

        // Dette er vores connectionPool (forbindelse)
        EntityManager em = emf.createEntityManager();


        personDAO.createPerson(p1);
        studentDAO.createStudent(s1);
        courseDAO.createCourse(c1);

        //Examples
        // Read
        /*
        Unicorn foundUnicorn = unicornDAO.findById(createdUnicorn.getId());
        System.out.println("Found Unicorn: " + foundUnicorn.getName());
         */

        // Update
        /*
        foundUnicorn.setAge(6);
        Unicorn updatedUnicorn = unicornDAO.update(foundUnicorn);
        System.out.println("Updated Unicorn Age: " + updatedUnicorn.getAge());
         */

        // Delete
        //unicornDAO.delete(createdUnicorn.getId());


        em.close();
        emf.close();

    }
}