package TestExams.Е01.P03_bakery;

import java.util.ArrayList;
import java.util.List;

public class Bakery {
    private List<Employee> employees;
    private String name;
    private int capacity;

    public Bakery(String name, int capacity) {
        this.employees = new ArrayList<>();
        this.name = name;
        this.capacity = capacity;
    }

    public void add(Employee employee) {
        if (capacity > 0) {
            this.employees.add(employee);
            this.capacity--;
        }
    }

    public boolean remove(String name) {
        for (Employee employee : employees) {
            if (employee.getName().equals(name)) {
                employees.remove(employee);
                return true;
            }
        }
        return false;
    }

    public Employee getOldestEmployee() {
        int oldest = -100;
        for (Employee employee : employees) {
            if (employee.getAge() > oldest) {
                oldest = employee.getAge();
            }
        }
        for (Employee employee : employees) {
            if (employee.getAge() == oldest) {
                return employee;
            }
        }
        return null;
    }

    public Employee getEmployee(String name) {
        return employees.stream().filter(e -> e.getName().equals(name)).findFirst().orElse(null);
    }

    public int getCount() {
        return employees.size();
    }

    public String report() {
        StringBuilder builder = new StringBuilder();

        builder.append("Employees working at Bakery ").append(this.name).append(":").append(System.lineSeparator());
        for (Employee employee : employees) {
            builder.append(employee);
            builder.append(System.lineSeparator());
        }

        return builder.toString().trim();
    }
}
