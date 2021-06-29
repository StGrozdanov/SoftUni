package T02_Encapsulation.Lab.P03_ValidationData;

public class Person {
    private String firstName;
    private String lastName;
    private int age;
    private double salary;

    public Person(String firstName, String lastName, int age, double salary) {
        setFirstName(firstName);
        setLastName(lastName);
        setAge(age);
        setSalary(salary);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        if (salary < 460) {
            throw new IllegalArgumentException("Salary cannot be less than 460!");
        }
        this.salary = salary;
    }

    public void setFirstName(String firstName) {
        if (firstName.trim().length() < 3) {
            throw new IllegalArgumentException("Names cannot be less than 3 symbols!");
        }
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        if (lastName.trim().length() < 3) {
            throw new IllegalArgumentException("Names cannot be less than 3 symbols!");
        }
        this.lastName = lastName;
    }

    public void setAge(int age) {
        if (age <= 0) {
            throw new IllegalArgumentException("Age cannot be zero or negative!");
        }
        this.age = age;
    }

    public void increaseSalary(double bonus) {
        if (this.getAge() >= 30) {
            this.setSalary(this.getSalary() + (this.getSalary() * (bonus / 100)));
        } else {
            this.setSalary(this.getSalary() + (this.getSalary() * (bonus / 200)));
        }
    }

    @Override
    public String toString() {
        return String.format("%s %s gets %f leva", firstName, lastName, salary);
    }
}
