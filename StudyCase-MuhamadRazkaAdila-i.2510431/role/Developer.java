package StudyCase.employee.role;

import StudyCase.employee.model.Employee;

public class Developer extends Employee {
    private String programmingLanguage;

    public Developer(String id, String name, String email, String programmingLanguage) {
        super(id, name, email);
        this.programmingLanguage = programmingLanguage;
    }

    public String getProgrammingLanguage() { return programmingLanguage; }
    public void setProgrammingLanguage(String lang) { this.programmingLanguage = lang; }

    @Override
    public String getRoleName() { return "Developer"; }

    @Override
    public double calculateBonus(double baseSalary) {
        return baseSalary * 0.15; // 15% bonus
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.printf("Lang   : %s%n", programmingLanguage);
        System.out.printf("Bonus  : 15%% of base salary%n");
    }
}
