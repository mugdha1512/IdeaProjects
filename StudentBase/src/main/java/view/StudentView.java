package view;

import java.util.*;
import controller.StudentController;
import model.Student;

public class StudentView {
    @Autowired
    private StudentController controller = new StudentController();
    private Scanner sc = new Scanner(System.in);

    public void showMenu() {
        while (true) {
            System.out.println("\n--- Student Management System ---");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();
            sc.nextLine(); // Clear buffer

            switch (choice) {
                case 1: addStudent(); break;
                case 2: viewStudents(); break;
                case 3: updateStudent(); break;
                case 4: deleteStudent(); break;
                case 5: System.out.println("Thank you!"); return;
                default: System.out.println("Invalid choice.");
            }
        }
    }

    private void addStudent() {
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("Age: ");
        int age = sc.nextInt();
        sc.nextLine();
        System.out.print("Course: ");
        String course = sc.nextLine();

        controller.addStudent(new Student(name, age, course));
    }

    private void viewStudents() {
        List<Student> students = controller.getAllStudents();
        System.out.println("\n--- Student List ---");
        for (Student s : students) {
            System.out.println("ID: " + s.getId() +
                    ", Name: " + s.getName() +
                    ", Age: " + s.getAge() +
                    ", Course: " + s.getCourse());
        }
    }

    private void updateStudent() {
        System.out.print("Enter ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("New Name: ");
        String name = sc.nextLine();
        System.out.print("New Age: ");
        int age = sc.nextInt();
        sc.nextLine();
        System.out.print("New Course: ");
        String course = sc.nextLine();

        controller.updateStudent(new Student(id, name, age, course));
    }

    private void deleteStudent() {
        System.out.print("Enter ID to delete: ");
        int id = sc.nextInt();
        controller.deleteStudent(id);
    }
}
