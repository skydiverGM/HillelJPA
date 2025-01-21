package ua.hillel;


import java.time.LocalDate;

public class App {
    public static void main(String[] args) {
        Student student = new Student("Jack", "Russell", "russell@gmail.com");
        StudentDAO studentDAO = new StudentDAO();
        Homework homework1 = new Homework("Write code", LocalDate.of(2025, 1, 31), null);
        Homework homework2 = new Homework("Write tests", LocalDate.of(2025, 1, 30), null);


        student.addHomework(homework1);
        student.addHomework(homework2);
        System.out.println(student.getHomeworks());
        studentDAO.save(student);
        Student s = studentDAO.findById(student.getId());
        Homework h = s.getHomeworks().stream().findAny().get();
        s.removeHomework(h);
        studentDAO.update(s);
        System.out.println(s);
        Student s1 = studentDAO.findById(student.getId());
        System.out.println(s1.getHomeworks());

    }
}
