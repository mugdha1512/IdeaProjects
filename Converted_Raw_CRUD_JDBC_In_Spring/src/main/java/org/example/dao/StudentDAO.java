package org.example.dao;

import org.example.model.Student;

import java.util.List;

public interface StudentDAO {
    void save(Student student);
    List<Student> getAll();
    Student getById(int id);
    void update(Student student);
    void delete(int id);
}
