package service;

import model.Student;

import java.util.List;

public interface StudentService {
    void addStudent(Student s);
    List<Student> getAllStudents();
    void updateStudent(Student s);
    void deleteStudent(int id);
}
