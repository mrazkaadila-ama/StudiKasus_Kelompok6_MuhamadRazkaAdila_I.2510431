package StudyCase.employee;

import StudyCase.employee.manager.EmployeeManager;
import StudyCase.employee.model.Employee;
import StudyCase.employee.role.*;
import java.util.Scanner;

public class MainEmployee {

    static Scanner scanner = new Scanner(System.in);
    static EmployeeManager empManager = new EmployeeManager();

    public static void main(String[] args) {
        // Pre-loaded sample data
        empManager.addEmployee(new Manager("EMP-001", "Budi Santoso", "budi@company.com", "Engineering"));
        empManager.addEmployee(new Developer("EMP-002", "Rina Wijaya", "rina@company.com", "Java"));
        empManager.addEmployee(new Developer("EMP-003", "Andi Nugroho", "andi@company.com", "Python"));
        empManager.addEmployee(new Intern("EMP-004", "Siti Rahma", "siti@company.com", "Universitas Brawijaya", 6));

        System.out.println("==========================================");
        System.out.println("    EMPLOYEE MANAGEMENT SYSTEM v1.0      ");
        System.out.println("==========================================");

        while (true) {
            System.out.println("\n=== MAIN MENU ===");
            System.out.println("1. Display all employees");
            System.out.println("2. Add new employee");
            System.out.println("3. Search employee by ID");
            System.out.println("4. Remove employee");
            System.out.println("5. Filter by role");
            System.out.println("6. Calculate bonus");
            System.out.println("0. Exit");
            System.out.print("Select menu (0-6): ");

            int choice = readInt();

            switch (choice) {
                case 1 -> empManager.displayAll();
                case 2 -> menuAddEmployee();
                case 3 -> menuSearchEmployee();
                case 4 -> menuRemoveEmployee();
                case 5 -> menuFilterByRole();
                case 6 -> menuCalculateBonus();
                case 0 -> {
                    System.out.println("Thank you for using Employee Management System. Goodbye!");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid choice! Please enter 0-6.");
            }
        }
    }

    // ─── ADD EMPLOYEE ───────────────────────────────────────────────
    private static void menuAddEmployee() {
        System.out.println("\n=== ADD NEW EMPLOYEE ===");
        System.out.println("Select role:");
        System.out.println("1. Manager");
        System.out.println("2. Developer");
        System.out.println("3. Intern");
        System.out.print("Role choice (1-3): ");
        int roleChoice = readInt();

        if (roleChoice < 1 || roleChoice > 3) {
            System.out.println("Invalid role choice!");
            return;
        }

        System.out.print("Employee ID (e.g. EMP-005): ");
        String id = scanner.nextLine().trim();

        System.out.print("Full Name               : ");
        String name = scanner.nextLine().trim();

        System.out.print("Email                   : ");
        String email = scanner.nextLine().trim();

        try {
            Employee newEmp = switch (roleChoice) {
                case 1 -> {
                    System.out.print("Department              : ");
                    String dept = scanner.nextLine().trim();
                    yield new Manager(id, name, email, dept);
                }
                case 2 -> {
                    System.out.print("Programming Language    : ");
                    String lang = scanner.nextLine().trim();
                    yield new Developer(id, name, email, lang);
                }
                case 3 -> {
                    System.out.print("University              : ");
                    String univ = scanner.nextLine().trim();
                    System.out.print("Duration (months, 1-12) : ");
                    int months = readInt();
                    yield new Intern(id, name, email, univ, months);
                }
                default -> throw new IllegalArgumentException("Invalid role");
            };
            empManager.addEmployee(newEmp);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // ─── SEARCH ─────────────────────────────────────────────────────
    private static void menuSearchEmployee() {
        System.out.print("\nEnter Employee ID to search: ");
        String id = scanner.nextLine().trim();
        Employee emp = empManager.findById(id);
        if (emp == null) {
            System.out.println("Employee with ID \"" + id + "\" not found.");
        } else {
            System.out.println("\n=== EMPLOYEE FOUND ===");
            emp.displayInfo();
        }
    }

    // ─── REMOVE ─────────────────────────────────────────────────────
    private static void menuRemoveEmployee() {
        empManager.displayAll();
        System.out.print("\nEnter Employee ID to remove: ");
        String id = scanner.nextLine().trim();

        Employee emp = empManager.findById(id);
        if (emp == null) {
            System.out.println("Employee with ID \"" + id + "\" not found.");
            return;
        }

        System.out.printf("Are you sure you want to remove \"%s\"? (y/n): ", emp.getName());
        String confirm = scanner.nextLine().trim();
        if (confirm.equalsIgnoreCase("y")) {
            empManager.removeEmployee(id);
        } else {
            System.out.println("Remove cancelled.");
        }
    }

    // ─── FILTER BY ROLE ─────────────────────────────────────────────
    private static void menuFilterByRole() {
        System.out.println("\nSelect role to filter:");
        System.out.println("1. Manager");
        System.out.println("2. Developer");
        System.out.println("3. Intern");
        System.out.print("Choice (1-3): ");
        int choice = readInt();

        String role = switch (choice) {
            case 1 -> "Manager";
            case 2 -> "Developer";
            case 3 -> "Intern";
            default -> null;
        };

        if (role == null) {
            System.out.println("Invalid choice!");
        } else {
            empManager.displayByRole(role);
        }
    }

    // ─── CALCULATE BONUS ────────────────────────────────────────────
    private static void menuCalculateBonus() {
        System.out.print("\nEnter Employee ID: ");
        String id = scanner.nextLine().trim();

        Employee emp = empManager.findById(id);
        if (emp == null) {
            System.out.println("Employee not found!");
            return;
        }

        System.out.print("Enter base salary (Rp): ");
        double salary = readDouble();
        if (salary <= 0) {
            System.out.println("Salary must be greater than 0!");
            return;
        }

        empManager.displayBonus(id, salary);
    }

    // ─── INPUT HELPERS ───────────────────────────────────────────────
    private static int readInt() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Input must be a number!");
            return -1;
        }
    }

    private static double readDouble() {
        try {
            return Double.parseDouble(scanner.nextLine().trim().replace(",", ""));
        } catch (NumberFormatException e) {
            System.out.println("Input must be a number!");
            return -1;
        }
    }
}
