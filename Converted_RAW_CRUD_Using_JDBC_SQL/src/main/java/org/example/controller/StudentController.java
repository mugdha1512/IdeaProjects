package org.example.controller;

import org.example.dao.StudentDAO;
import org.example.dao.StudentDAOImpl;
import org.example.model.Student;

import java.util.List;

public class StudentController {
    private final StudentDAO studentDAO;

    public StudentController() {
        this.studentDAO = new StudentDAOImpl();
    }

    public void createStudent(String name, String email) {
        Student student = new Student(0, name, email);
        studentDAO.create(student);
        System.out.println("Student created successfully");
    }

    public Student getStudent(int id) {
        Student student = studentDAO.read(id);
        if (student == null) {
            System.out.println("Student not found");
        }
        return student;
    }

    public void updateStudent(int id, String name, String email) {
        Student student = new Student(id, name, email);
        studentDAO.update(student);
        System.out.println("Student updated successfully");
    }

    public void deleteStudent(int id) {
        studentDAO.delete(id);
        System.out.println("Student deleted successfully");
    }

    public List<Student> getAllStudents() {
        return studentDAO.getAll();
    }
}
