package StudyCase.employee.model;

public abstract class Employee {
    protected String id;
    protected String name;
    protected String email;
    protected boolean isActive;

    public Employee(String id, String name, String email) {
        this.id = id;
        setName(name);
        setEmail(email);
        this.isActive = true;
    }

    // Getters
    public String getId()    { return id; }
    public String getName()  { return name; }
    public String getEmail() { return email; }
    public boolean isActive() { return isActive; }

    // Setters with validation
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty!");
        }
        this.name = name.trim();
    }

    public void setEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Email format is invalid!");
        }
        this.email = email.trim();
    }

    public void activate()   { this.isActive = true; }
    public void deactivate() { this.isActive = false; }

    // Abstract methods — each role must implement
    public abstract String getRoleName();
    public abstract double calculateBonus(double baseSalary);

    public void displayInfo() {
        System.out.printf("ID     : %s%n", id);
        System.out.printf("Name   : %s%n", name);
        System.out.printf("Email  : %s%n", email);
        System.out.printf("Role   : %s%n", getRoleName());
        System.out.printf("Status : %s%n", isActive ? "Active" : "Inactive");
    }
}
