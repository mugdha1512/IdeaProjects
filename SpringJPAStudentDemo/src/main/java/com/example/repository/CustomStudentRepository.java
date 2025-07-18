package com.example.repository;

import com.example.entity.Student;

import java.util.List;

public interface CustomStudentRepository {
    List<Student> findStudentsByTradeAndSport(String tradeName, String sportName);
}