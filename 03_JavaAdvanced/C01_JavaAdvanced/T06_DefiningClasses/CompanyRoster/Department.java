package T06_DefiningClasses.Exercise.CompanyRoster;

import java.util.ArrayList;
import java.util.List;

public class Department {

    private String name;
    private List<Employee> employeeList;

    public Department() {
        this.employeeList = new ArrayList<>();
    }

    public void addEmployees(Employee employee) {
        this.employeeList.add(employee);
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public double getAvgSalary() {
//        double avg = employeeList.stream().mapToDouble(Employee::getSalary).average().orElse(0.0);
        double avgSalary = 0.0;
        for (Employee employee : employeeList) {
            avgSalary += employee.getSalary();
        }
        return avgSalary / this.employeeList.size();
    }
}
