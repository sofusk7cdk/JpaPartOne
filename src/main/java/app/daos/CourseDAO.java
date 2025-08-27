package app.daos;

import app.entities.Course;
import app.entities.Person;
import app.entities.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class CourseDAO {

    private final EntityManagerFactory emf;

    public CourseDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public Course findById(int id) {
        try(EntityManager em = emf.createEntityManager()) {
            Course foundCourse = em.find(Course.class, id);
            em.close();
            return foundCourse;
        }
    }

    public Course createCourse(Course course){
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(course);
            em.getTransaction().commit();
            return course;
        }
    }


    public Course update(Course course){
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            Course updatedCourse = em.merge(course);
            em.getTransaction().commit();
            em.close();
            return updatedCourse;
        }
    }


    public void delete(int id) {
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Course course = findById(id);
            if (course != null) {
                em.remove(course);
            }
            em.getTransaction().commit();
            em.close();
        }
    }

    public List<Course> findAll() {
        try(EntityManager em = emf.createEntityManager()) {
            TypedQuery<Course> query = em.createQuery("SELECT name FROM Course name", Course.class);
            return query.getResultList();
        }
    }
}
