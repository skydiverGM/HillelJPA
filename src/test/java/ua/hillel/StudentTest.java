package ua.hillel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

class StudentTest {
    private Student student;
    private Homework homework;

    @BeforeEach
    public void setUp() {
        student = new Student("Jack", "Russell", "russell@hillel.ua");
        homework = new Homework("Write tests", LocalDate.of(2025, 1, 30), null);
    }

    @Test
    void addHomework_MethodCall_HomeworkAddedToSet() {
        assertTrue(student.getHomeworks().isEmpty());
        student.addHomework(homework);
        assertTrue(student.getHomeworks().contains(homework));
    }

    @Test
    void removeHomework_MethodCall_HomeworkRemovedFromSet() {
        assertTrue(student.getHomeworks().isEmpty());
        student.addHomework(homework);
        assertTrue(student.getHomeworks().contains(homework));
        student.removeHomework(homework);
        assertTrue(student.getHomeworks().isEmpty());
    }
}
