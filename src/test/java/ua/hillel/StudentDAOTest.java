package ua.hillel;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentDAOTest {

    private StudentDAO dao;
    private Student student;

    @BeforeEach
    void setUpEach() {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.createNativeQuery("SET REFERENTIAL_INTEGRITY FALSE").executeUpdate();
        em.createNativeQuery("TRUNCATE TABLE homeworks RESTART IDENTITY").executeUpdate();
        em.createNativeQuery("TRUNCATE TABLE students RESTART IDENTITY").executeUpdate();
        em.createNativeQuery("SET REFERENTIAL_INTEGRITY TRUE").executeUpdate();
        tx.commit();
        em.close();

        student = new Student("Jack", "Russell", "russell@hillel.ua");
        dao = new StudentDAO();
        dao.save(student);
    }

    @Test
    void save_Students_SavedToDBAndIdReturned() {
        assertNotNull(student.getId());
    }

    @Test
    void findById_StudentId_ReturnsStudent() {
        Student s = dao.findById(student.getId());

        assertNotNull(s);
        assertEquals("Jack", s.getFirstName());
        assertEquals("Russell", s.getLastName());
        assertEquals("russell@hillel.ua", s.getEmail());
    }

    @Test
    void update_StudentUpdate_UpdatedInDB() {
        student.setFirstName("Stanly");
        Student s = dao.update(student);
        assertNotNull(s);
        assertEquals(student.getId(), s.getId());
        assertEquals("Stanly", s.getFirstName());
        assertEquals("Russell", s.getLastName());
        assertEquals("russell@hillel.ua", s.getEmail());
    }

    @Test
    void deleteById_StudentId_StudentRemovedFromDB() {
        assertTrue(dao.deleteById(student.getId()));
        assertFalse(dao.deleteById(student.getId()));
    }

    @Test
    void findAll_MethodCall_ReturnsAllStudents() {
        Student s = new Student("Stanly", "Thomson", "thomson@hillel.ua");
        dao.save(s);
        List<Student> students = dao.findAll();
        assertNotNull(students);
        assertEquals(2, students.size());
        assertEquals("Jack", students.get(0).getFirstName());
        assertEquals("Stanly", students.get(1).getFirstName());
    }

    @Test
    void findByEmail_Email_ReturnsStudent() {
        Student s = dao.findByEmail(student.getEmail());

        assertNotNull(s);
        assertEquals("Jack", s.getFirstName());
    }
}
