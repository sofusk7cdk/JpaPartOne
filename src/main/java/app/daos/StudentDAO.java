package app.daos;

import app.entities.Person;
import app.entities.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class StudentDAO {
    private final EntityManagerFactory emf;

    public StudentDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public Student findById(int id) {
        try(EntityManager em = emf.createEntityManager()) {
            Student foundStudent = em.find(Student.class, id);
            em.close();
            return foundStudent;
        }
    }

    public Student createStudent(Student student){
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(student);
            em.getTransaction().commit();
            return student;
        }
    }

    public Student update(Student student) {
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Student updatedStudent = em.merge(student);
            em.getTransaction().commit();
            em.close();
            return updatedStudent;
        }
    }

    public void delete(int id) {
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Student student = findById(id);
            if (student != null) {
                em.remove(student);
            }
            em.getTransaction().commit();
            em.close();
        }
    }
}
