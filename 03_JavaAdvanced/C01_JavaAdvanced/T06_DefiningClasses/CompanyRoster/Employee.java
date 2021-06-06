package T06_DefiningClasses.Exercise.CompanyRoster;

public class Employee {

    private String name;
    private double salary;
    private String position;
    private String department;
    private String email;
    private int age;

    public Employee(String name, double salary, String position, String department, int age, String email) {
        this.name = name;
        this.salary = salary;
        this.position = position;
        this.department = department;
        this.age = age;
        this.email = email;
    }

    public double getSalary() {
        return this.salary;
    }

    public Employee(String name, double salary, String position, String department) {
        this(name, salary, position, department, -1, "n/a");
    }

    public Employee(String name, double salary, String position, String department, String email) {
        this(name, salary, position, department, -1, email);
    }

    public Employee(String name, double salary, String position, String department, int age) {
        this(name, salary, position, department, age, "n/a");
    }

    @Override
    public String toString() {
        return String.format("%s %.2f %s %d", this.name, this.salary, this.email, this.age);
    }
}
