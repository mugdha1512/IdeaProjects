package com.example.studentapp.repository;

import com.example.studentapp.entity.Student;
import java.util.List;

public interface StudentRepository {
    Student save(Student student);
    Student findById(Long id);
    List<Student> findAll(int page, int size, String sortField, boolean asc);
    void delete(Long id);
    long count();
}
