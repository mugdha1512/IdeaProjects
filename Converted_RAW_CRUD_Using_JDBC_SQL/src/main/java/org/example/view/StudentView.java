package org.example.view;

import org.example.controller.StudentController;
import org.example.model.Student;

import java.util.Scanner;

public class StudentView {
    private final StudentController controller;
    private final Scanner scanner;

    public StudentView() {
        this.controller = new StudentController();
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        while (true) {
            System.out.println("\nStudent Management System");
            System.out.println("1. Create Student");
            System.out.println("2. View Student");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. List All Students");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> createStudent();
                case 2 -> viewStudent();
                case 3 -> updateStudent();
                case 4 -> deleteStudent();
                case 5 -> listAllStudents();
                case 6 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid option");
            }
        }
    }

    private void createStudent() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        controller.createStudent(name, email);
    }

    private void viewStudent() {
        System.out.print("Enter student ID: ");
        int id = scanner.nextInt();
        Student student = controller.getStudent(id);
        if (student != null) {
            System.out.println(student);
        }
    }

    private void updateStudent() {
        System.out.print("Enter student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter new name: ");
        String name = scanner.nextLine();
        System.out.print("Enter new email: ");
        String email = scanner.nextLine();
        controller.updateStudent(id, name, email);
    }

    private void deleteStudent() {
        System.out.print("Enter student ID: ");
        int id = scanner.nextInt();
        controller.deleteStudent(id);
    }

    private void listAllStudents() {
        System.out.println("\nAll Students:");
        for (Student student : controller.getAllStudents()) {
            System.out.println(student);
        }
    }
}
