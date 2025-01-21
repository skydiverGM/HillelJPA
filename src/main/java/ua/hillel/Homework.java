package ua.hillel;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "homeworks")
public class Homework {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private LocalDate deadline;
    private int mark;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    public Homework() {
    }

    public Homework(String description, LocalDate deadline, Student student) {
        this.description = description;
        this.deadline = deadline;
        this.student = student;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Homework homework = (Homework) o;
        return Objects.equals(id, homework.id) && Objects.equals(description, homework.description) && Objects.equals(deadline, homework.deadline) && Objects.equals(student, homework.student);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, deadline, student);
    }

    @Override
    public String toString() {
        return "Homework{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", deadline=" + deadline +
                ", mark=" + mark +
                ", student=" + (student != null ? student.getFirstName() + student.getLastName() : null) +
                '}';
    }
}
