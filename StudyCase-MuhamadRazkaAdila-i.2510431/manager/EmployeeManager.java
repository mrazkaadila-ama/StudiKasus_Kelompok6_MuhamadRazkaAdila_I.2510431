package StudyCase.employee.manager;

import StudyCase.employee.model.Employee;

public class EmployeeManager {
    private Employee[] employeeList;
    private int count;
    private static final int MAX_CAPACITY = 50;

    public EmployeeManager() {
        this.employeeList = new Employee[MAX_CAPACITY];
        this.count = 0;
    }

    // ADD
    public boolean addEmployee(Employee emp) {
        if (count >= MAX_CAPACITY) {
            System.out.println("Employee list is full! Max capacity: " + MAX_CAPACITY);
            return false;
        }
        if (findById(emp.getId()) != null) {
            System.out.println("Employee with ID \"" + emp.getId() + "\" already exists!");
            return false;
        }
        employeeList[count++] = emp;
        System.out.println("Employee \"" + emp.getName() + "\" added successfully.");
        return true;
    }

    // FIND BY ID
    public Employee findById(String id) {
        for (int i = 0; i < count; i++) {
            if (employeeList[i].getId().equalsIgnoreCase(id)) {
                return employeeList[i];
            }
        }
        return null;
    }

    // FIND INDEX BY ID (internal use)
    private int findIndexById(String id) {
        for (int i = 0; i < count; i++) {
            if (employeeList[i].getId().equalsIgnoreCase(id)) {
                return i;
            }
        }
        return -1;
    }

    // DELETE
    public boolean removeEmployee(String id) {
        int idx = findIndexById(id);
        if (idx == -1) {
            System.out.println("Employee with ID \"" + id + "\" not found!");
            return false;
        }
        String name = employeeList[idx].getName();
        // Shift elements left
        for (int i = idx; i < count - 1; i++) {
            employeeList[i] = employeeList[i + 1];
        }
        employeeList[--count] = null;
        System.out.println("Employee \"" + name + "\" removed successfully.");
        return true;
    }

    // DISPLAY ALL
    public void displayAll() {
        if (count == 0) {
            System.out.println("No employees registered yet.");
            return;
        }
        System.out.println("\n=== EMPLOYEE LIST (" + count + " total) ===");
        for (int i = 0; i < count; i++) {
            System.out.printf("%n[%d] ", (i + 1));
            employeeList[i].displayInfo();
            System.out.println("-----------------------------");
        }
    }

    // DISPLAY BY ROLE
    public void displayByRole(String roleName) {
        System.out.println("\n=== EMPLOYEES - Role: " + roleName.toUpperCase() + " ===");
        boolean found = false;
        for (int i = 0; i < count; i++) {
            if (employeeList[i].getRoleName().equalsIgnoreCase(roleName)) {
                employeeList[i].displayInfo();
                System.out.println("-----------------------------");
                found = true;
            }
        }
        if (!found) System.out.println("No employees found with role: " + roleName);
    }

    // CALCULATE BONUS
    public void displayBonus(String id, double baseSalary) {
        Employee emp = findById(id);
        if (emp == null) {
            System.out.println("Employee not found!");
            return;
        }
        double bonus = emp.calculateBonus(baseSalary);
        System.out.printf("Bonus for %s (%s): Rp %,.0f%n", emp.getName(), emp.getRoleName(), bonus);
    }

    public int getCount() { return count; }

    public Employee[] getAll() {
        Employee[] result = new Employee[count];
        for (int i = 0; i < count; i++) result[i] = employeeList[i];
        return result;
    }
}
