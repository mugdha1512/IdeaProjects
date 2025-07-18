package com.example.service;

import com.example.dao.StudentDAO;
import com.example.model.Student;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

public class TransactionService {

    private StudentDAO studentDAO;
    private JdbcTemplate jdbcTemplate;

    public void setStudentDAO(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public void insertStudentWithLog(Student student) {
        studentDAO.save(student);

        // insert into audit log
        String log = "Inserted student: " + student.getName();
        jdbcTemplate.update("INSERT INTO audit_log(message) VALUES(?)", log);
    }
}
