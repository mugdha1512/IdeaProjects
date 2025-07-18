package org.example.controller;

import org.example.dao.StudentDAO;
import org.example.model.Student;

import java.util.List;

public class StudentController {

    private StudentDAO studentDAO;

    public StudentController(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    public void addStudent(Student student) {
        studentDAO.save(student);
    }

    public List<Student> getAllStudents() {
        return studentDAO.getAll();
    }

    public Student getStudentById(int id) {
        return studentDAO.getById(id);
    }

    public void updateStudent(Student student) {
        studentDAO.update(student);
    }

    public void deleteStudent(int id) {
        studentDAO.delete(id);
    }
}
