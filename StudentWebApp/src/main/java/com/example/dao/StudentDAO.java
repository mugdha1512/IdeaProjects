package com.example.dao;

import com.example.model.Student;
import java.util.List;

public interface StudentDAO {
    int save(Student student);
    int update(Student student);
    int deleteById(int id);
    Student findById(int id);
    List<Student> findAll();
    List<Student> fetchStudentsWithDepartments();  // Optional
}
