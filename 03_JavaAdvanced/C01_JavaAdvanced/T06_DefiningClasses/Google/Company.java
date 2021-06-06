package T06_DefiningClasses.Exercise.Google;

public class Company {
    private String name;
    private String department;
    private double salary;

    public Company(String name, String department, double salary) {
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    @Override
    public String toString() {
        try {
            return String.format("%s %s %.2f%n", this.name, this.department, this.salary);
        } catch (NullPointerException exception) {
            return "";
        }
    }
}
