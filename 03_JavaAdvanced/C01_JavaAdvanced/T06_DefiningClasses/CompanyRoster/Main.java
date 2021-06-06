package T06_DefiningClasses.Exercise.CompanyRoster;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, Department> departments = new LinkedHashMap<>();
        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < n; i++) {

            String[] tokens = scanner.nextLine().split("\\s+");
            String name = tokens[0];
            double salary = Double.parseDouble(tokens[1]);
            String position = tokens[2];
            String department = tokens[3];

            Employee employee;
            departments.putIfAbsent(department, new Department());

            if (tokens.length == 6) {
                String email = tokens[4];
                int age = Integer.parseInt(tokens[5]);
                employee = new Employee(name, salary, position, department, age, email);
            } else if (tokens.length == 4) {
                employee = new Employee(name, salary, position, department);
            } else {
                if (tokens[4].matches("^\\d+$")) {
                    int age = Integer.parseInt(tokens[4]);
                    employee = new Employee(name, salary, position, department, age);
                } else {
                    String email = tokens[4];
                    employee = new Employee(name, salary, position, department, email);
                }
            }
            departments.get(department).addEmployees(employee);
        }

        double bestAvgSalary = Integer.MIN_VALUE;
        String bestDepartment = "";

        for (Map.Entry<String, Department> entry : departments.entrySet()) {
            if (entry.getValue().getAvgSalary() > bestAvgSalary) {
                bestAvgSalary = entry.getValue().getAvgSalary();
                bestDepartment = entry.getKey();
            }
        }

        Department department = departments.get(bestDepartment);

        System.out.printf("Highest Average Salary: %s%n", bestDepartment);
        for (Employee employee : department.getEmployeeList()) {
            System.out.println(employee.toString());
        }
    }
}
