package org.example;

import org.example.config.DatabaseConfig;
import org.example.controller.StudentController;
import org.example.dao.StudentDAO;
import org.example.dao.StudentDAOImpl;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class Main {
    public static void main(String[] args) {

        // Initialize Spring Container
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DatabaseConfig.class);

        JdbcTemplate jdbcTemplate = context.getBean(JdbcTemplate.class);
        StudentController controller = new StudentController((StudentDAO) new StudentDAOImpl(jdbcTemplate));
        org.example.view.StudentView view = new org.example.view.StudentView(controller);
        view.showMenu();

        context.close();
    }
}
