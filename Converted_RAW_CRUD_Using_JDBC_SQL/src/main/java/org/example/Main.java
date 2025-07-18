package org.example;

import org.example.config.DatabaseConfig;
import org.example.view.StudentView;

public class Main {
    public static void main(String[] args) {
        DatabaseConfig.initializeDatabase();
        StudentView view = new StudentView();
        view.displayMenu();
    }
}
