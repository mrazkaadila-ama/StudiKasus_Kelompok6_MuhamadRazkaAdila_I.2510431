package StudyCase.employee.role;

import StudyCase.employee.model.Employee;

public class Intern extends Employee {
    private String university;
    private int durationMonths;

    public Intern(String id, String name, String email, String university, int durationMonths) {
        super(id, name, email);
        this.university = university;
        setDurationMonths(durationMonths);
    }

    public String getUniversity()      { return university; }
    public int getDurationMonths()     { return durationMonths; }
    public void setUniversity(String u){ this.university = u; }

    public void setDurationMonths(int months) {
        if (months < 1 || months > 12) {
            throw new IllegalArgumentException("Internship duration must be between 1 and 12 months!");
        }
        this.durationMonths = months;
    }

    @Override
    public String getRoleName() { return "Intern"; }

    @Override
    public double calculateBonus(double baseSalary) {
        return baseSalary * 0.05; // 5% bonus
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.printf("Univ   : %s%n", university);
        System.out.printf("Period : %d month(s)%n", durationMonths);
        System.out.printf("Bonus  : 5%% of base salary%n");
    }
}
