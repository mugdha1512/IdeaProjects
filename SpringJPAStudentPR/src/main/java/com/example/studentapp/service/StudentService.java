package com.example.studentapp.service;

import com.example.studentapp.entity.Student;
import java.util.List;

public interface StudentService {
    Student addStudent(Student student);
    Student getStudent(Long id);
    List<Student> getAllStudents(int page, int size, String sortField, boolean asc);
    void deleteStudent(Long id);
}
