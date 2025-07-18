package com.example.studentapp.service;

import com.example.studentapp.entity.Student;
import com.example.studentapp.exception.StudentNotFoundException;
import com.example.studentapp.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    @Cacheable(value = "students", key = "#id")
    public Student getStudent(Long id) {
        Student student = studentRepository.findById(id);
        if (student == null)
            throw new StudentNotFoundException("Student not found with ID: " + id);
        return student;
    }

    @Override
    public List<Student> getAllStudents(int page, int size, String sortField, boolean asc) {
        return studentRepository.findAll(page, size, sortField, asc);
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.delete(id);
    }
}
