package com.example.console;

import com.example.entity.Sport;
import com.example.entity.Student;
import com.example.entity.Trade;
import com.example.repository.SportRepository;
import com.example.repository.StudentRepository;
import com.example.repository.StudentStatsRepository;
import com.example.repository.TradeRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.*;

public class ConsoleApp {
    private static StudentRepository studentRepository;
    private static TradeRepository tradeRepository;
    private static SportRepository sportRepository;
    private static StudentStatsRepository studentStatsRepository;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.example.config");
        studentRepository = context.getBean(StudentRepository.class);
        tradeRepository = context.getBean(TradeRepository.class);
        sportRepository = context.getBean(SportRepository.class);
        studentStatsRepository = context.getBean(StudentStatsRepository.class);

        // Initialize sample data
        initializeData();

        while (true) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            switch (choice) {
                case 1:
                    createStudent();
                    break;
                case 2:
                    readStudents();
                    break;
                case 3:
                    updateStudent();
                    break;
                case 4:
                    deleteStudent();
                    break;
                case 5:
                    createTrade();
                    break;
                case 6:
                    readTrades();
                    break;
                case 7:
                    createSport();
                    break;
                case 8:
                    readSports();
                    break;
                case 9:
                    findStudentsByTrade();
                    break;
                case 10:
                    findStudentsBySportCount();
                    break;
                case 11:
                    findStudentsByTradePaginated();
                    break;
                case 12:
                    findStudentsByTradeAndSport();
                    break;
                case 13:
                    findStudentsByEmail();
                    break;
                case 0:
                    context.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\n=== Student Management System ===");
        System.out.println("1. Create Student");
        System.out.println("2. List All Students");
        System.out.println("3. Update Student");
        System.out.println("4. Delete Student");
        System.out.println("5. Create Trade");
        System.out.println("6. List All Trades");
        System.out.println("7. Create Sport");
        System.out.println("8. List All Sports");
        System.out.println("9. Find Students by Trade");
        System.out.println("10. Find Students with Sport Count > N");
        System.out.println("11. Paginated Students by Trade");
        System.out.println("12. Find Students by Trade and Sport (Custom Repository)");
        System.out.println("13. Find Students by Email (RepositoryDefinition)");
        System.out.println("0. Exit");
        System.out.print("Enter choice: ");
    }

    private static void initializeData() {
        Trade cs = new Trade("Computer Science");
        Trade mech = new Trade("Mechanical Engineering");
        tradeRepository.saveAll(Arrays.asList(cs, mech));

        Sport football = new Sport("Football");
        Sport basketball = new Sport("Basketball");
        sportRepository.saveAll(Arrays.asList(football, basketball));

        Student s1 = new Student("John", "Doe", "john.doe@example.com", cs);
        s1.getSports().addAll(Arrays.asList(football, basketball));
        Student s2 = new Student("Jane", "Smith", "jane.smith@example.com", mech);
        s2.getSports().add(football);
        studentRepository.saveAll(Arrays.asList(s1, s2));
    }

    private static void createStudent() {
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter trade name: ");
        String tradeName = scanner.nextLine();
        Trade trade = tradeRepository.findByName(tradeName);
        if (trade == null) {
            System.out.println("Trade not found!");
            return;
        }
        Student student = new Student(firstName, lastName, email, trade);
        System.out.print("Enter sport names (comma-separated, or none): ");
        String sportsInput = scanner.nextLine();
        if (!sportsInput.isEmpty()) {
            String[] sportNames = sportsInput.split(",");
            for (String name : sportNames) {
                Sport sport = sportRepository.findByName(name.trim());
                if (sport != null) {
                    student.getSports().add(sport);
                }
            }
        }
        studentRepository.save(student);
        System.out.println("Student created: " + student);
    }

    private static void readStudents() {
        System.out.println("\nAll Students:");
        studentRepository.findAll().forEach(System.out::println);
    }

    private static void updateStudent() {
        System.out.print("Enter student ID: ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        Optional<Student> studentOpt = studentRepository.findById(id);
        if (studentOpt.isPresent()) {
            Student student = studentOpt.get();
            System.out.print("Enter new first name (or press Enter to keep '" + student.getFirstName() + "'): ");
            String firstName = scanner.nextLine();
            if (!firstName.isEmpty()) student.setFirstName(firstName);
            System.out.print("Enter new trade name (or press Enter to keep '" + student.getTrade().getName() + "'): ");
            String tradeName = scanner.nextLine();
            if (!tradeName.isEmpty()) {
                Trade trade = tradeRepository.findByName(tradeName);
                if (trade != null) student.setTrade(trade);
            }
            studentRepository.save(student);
            System.out.println("Student updated: " + student);
        } else {
            System.out.println("Student not found!");
        }
    }

    private static void deleteStudent() {
        System.out.print("Enter student ID: ");
        Long id = scanner.nextLong();
        studentRepository.deleteById(id);
        System.out.println("Student deleted (ID: " + id + ")");
    }

    private static void createTrade() {
        System.out.print("Enter trade name: ");
        String name = scanner.nextLine();
        Trade trade = new Trade(name);
        tradeRepository.save(trade);
        System.out.println("Trade created: " + trade);
    }

    private static void readTrades() {
        System.out.println("\nAll Trades:");
        tradeRepository.findAll().forEach(System.out::println);
    }

    private static void createSport() {
        System.out.print("Enter sport name: ");
        String name = scanner.nextLine();
        Sport sport = new Sport(name);
        sportRepository.save(sport);
        System.out.println("Sport created: " + sport);
    }

    private static void readSports() {
        System.out.println("\nAll Sports:");
        sportRepository.findAll().forEach(System.out::println);
    }

    private static void findStudentsByTrade() {
        System.out.print("Enter trade name: ");
        String tradeName = scanner.nextLine();
        Trade trade = tradeRepository.findByName(tradeName);
        if (trade != null) {
            System.out.println("\nStudents in " + tradeName + ":");
            studentRepository.findByTradeId(trade.getId()).forEach(System.out::println);
        } else {
            System.out.println("Trade not found!");
        }
    }

    private static void findStudentsBySportCount() {
        System.out.print("Enter minimum sport count: ");
        int count = scanner.nextInt();
        System.out.println("\nStudents with more than " + count + " sports:");
        studentRepository.findStudentsWithSportsGreaterThan(count).forEach(System.out::println);
    }

    private static void findStudentsByTradePaginated() {
        System.out.print("Enter trade name: ");
        String tradeName = scanner.nextLine();
        Trade trade = tradeRepository.findByName(tradeName);
        if (trade != null) {
            System.out.print("Enter page number (0-based): ");
            int page = scanner.nextInt();
            System.out.print("Enter page size: ");
            int size = scanner.nextInt();
            scanner.nextLine();
            Page<Student> students = studentRepository.findByTradeId(trade.getId(), PageRequest.of(page, size, Sort.by("lastName").ascending()));
            System.out.println("\nStudents in " + tradeName + " (Page " + page + ", Size " + size + "):");
            students.forEach(System.out::println);
        } else {
            System.out.println("Trade not found!");
        }
    }

    private static void findStudentsByTradeAndSport() {
        System.out.print("Enter trade name: ");
        String tradeName = scanner.nextLine();
        System.out.print("Enter sport name: ");
        String sportName = scanner.nextLine();
        System.out.println("\nStudents in " + tradeName + " playing " + sportName + ":");
        studentRepository.findStudentsByTradeAndSport(tradeName, sportName).forEach(System.out::println);
    }

    private static void findStudentsByEmail() {
        System.out.print("Enter email fragment: ");
        String email = scanner.nextLine();
        System.out.println("\nStudents with email containing '" + email + "':");
        studentStatsRepository.findByEmailContainingIgnoreCase(email).forEach(System.out::println);
    }
}