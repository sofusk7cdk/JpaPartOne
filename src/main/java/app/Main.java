package app;

import app.config.HibernateConfig;
import app.daos.CourseDAO;
import app.daos.PersonDAO;
import app.daos.StudentDAO;
import app.entities.Course;
import app.entities.Person;
import app.entities.Student;
import app.entities.StudentStatus;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.time.LocalDate;

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
                .status(StudentStatus.ACTIVE)
                .dayOfBirth(LocalDate.of(2014,8,9))
                .courseId(1)
                .build();

        Student s2 = Student.builder()
                .name("Mulle")
                .phoneNumber("+45 34023388")
                .email("mulle@kkkkkkkk.dk")
                .address("Vejen")
                .status(StudentStatus.INACTIVE)
                .dayOfBirth(LocalDate.of(2010,3,9))
                .build();

        Student s3 = Student.builder()
                .name("Lars")
                .phoneNumber("+45 34023388")
                .email("lars@kkkkkkkk.dk")
                .address("gaden")
                .status(StudentStatus.GRADUATED)
                .dayOfBirth(LocalDate.of(1990,5,28))
                .build();

        Student s4 = Student.builder()
                .name("Victor")
                .phoneNumber("+45 34023388")
                .email("sofus@kkkkkkkk.dk")
                .address("Vejen")
                .status(StudentStatus.ACTIVE)
                .dayOfBirth(LocalDate.of(2014,8,9))
                .courseId(1)
                .build();


        Course c1 = Course.builder()
                .name("Software")
                .teacherName("Jon")
                .semester("3 semester")
                .classroom("3. 7")
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
        studentDAO.createStudent(s2);
        studentDAO.createStudent(s3);
        studentDAO.createStudent(s4);
        courseDAO.createCourse(c1);

        Student foundStudent = studentDAO.findById(s1.getId());
        System.out.println("Found Student: " + foundStudent.getName());

        foundStudent.setName("Filip");
        Student updatedStudent = studentDAO.update(foundStudent);
        System.out.println("Updated Student Name: " + updatedStudent.getName());

        //studentDAO.delete(updatedStudent.getId());

        System.out.println("All students");
        studentDAO.findAll().forEach(System.out::println);

        System.out.println();

        System.out.println("All courses");
        courseDAO.findAll().forEach(System.out::println);

        System.out.println();

        System.out.println("Studens for specific course id");
        studentDAO.findAllStudentForSpecificCourse(1).forEach(System.out::println);



        em.close();
        emf.close();

    }
}