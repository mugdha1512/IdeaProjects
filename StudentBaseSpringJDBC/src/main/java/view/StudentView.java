package view;

import controller.StudentController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class StudentView {
    @Autowired
    private StudentController controller;

    public void showMenu() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("--- Student Management ---\n1. Add\n2. View\n3. Update\n4. Delete\n5. Exit");
            int choice = sc.nextInt();
            switch (choice) {
                case 1: /* call controller.add */ break;
                case 2: /* call controller.viewAll */ break;
                // etc.
                case 5: return;
            }
        }
    }
}