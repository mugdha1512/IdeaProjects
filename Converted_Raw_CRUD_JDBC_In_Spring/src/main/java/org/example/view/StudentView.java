package org.example.view;

import org.example.controller.StudentController;
import org.example.model.Student;

import java.util.List;
import java.util.Scanner;

public class StudentView {

    private StudentController controller;
    private Scanner scanner;

    public StudentView(StudentController controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n1. Add Student");
            System.out.println("2. List Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1: addStudent(); break;
                case 2: listStudents(); break;
                case 3: updateStudent(); break;
                case 4: deleteStudent(); break;
                case 5: System.exit(0);
                default: System.out.println("Invalid choice.");
            }
        }
    }

    private void addStudent() {
        System.out.print("Enter Name: ");
        String name = scanner.next();
        System.out.print("Enter Email: ");
        String email = scanner.next();

        Student student = new Student();
        student.setName(name);
        student.setEmail(email);

        controller.addStudent(student);
        System.out.println("Student added successfully.");
    }

    private void listStudents() {
        List<Student> students = controller.getAllStudents();
        for (Student s : students) {
            System.out.println(s.getId() + " " + s.getName() + " " + s.getEmail());
        }
    }

    private void updateStudent() {
        System.out.print("Enter Student ID: ");
        int id = scanner.nextInt();
        System.out.print("Enter New Name: ");
        String name = scanner.next();
        System.out.print("Enter New Email: ");
        String email = scanner.next();

        Student student = new Student(id, name, email);
        controller.updateStudent(student);
        System.out.println("Student updated successfully.");
    }

    private void deleteStudent() {
        System.out.print("Enter Student ID to delete: ");
        int id = scanner.nextInt();
        controller.deleteStudent(id);
        System.out.println("Student deleted successfully.");
    }
}
