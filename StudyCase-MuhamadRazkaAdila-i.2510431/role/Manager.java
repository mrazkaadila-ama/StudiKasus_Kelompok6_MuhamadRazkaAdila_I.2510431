package StudyCase.employee.role;

import StudyCase.employee.model.Employee;

public class Manager extends Employee {
    private String department;

    public Manager(String id, String name, String email, String department) {
        super(id, name, email);
        this.department = department;
    }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    @Override
    public String getRoleName() { return "Manager"; }

    @Override
    public double calculateBonus(double baseSalary) {
        return baseSalary * 0.20; // 20% bonus
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.printf("Dept   : %s%n", department);
        System.out.printf("Bonus  : 20%% of base salary%n");
    }
}
