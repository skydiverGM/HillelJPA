package ua.hillel;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

import java.util.Optional;

public class StudentDAO extends AbstractGenericDAO<Student, Long> {

    @Override
    protected Class<Student> getEntityClass() {
        return Student.class;
    }

    public Student findByEmail(String email) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT s FROM Student s WHERE s.email = :email", Student.class)
                            .setParameter("email", email)
                            .getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
}
