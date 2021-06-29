package T01_WorkingWithAbstraction.Lab.P03_StudentsSystem;

import java.util.HashMap;
import java.util.Map;

public class StudentSystem {
    private Map<String, Student> studentMap;

    public StudentSystem() {
        this.studentMap = new HashMap<>();
    }

    public void add(Student student) {
        this.studentMap.putIfAbsent(student.getName(), student);
    }

    public void showStudent(String name) {
        if (this.studentMap.containsKey(name)) {
            var student = studentMap.get(name);
            StringBuilder builder = new StringBuilder();
            String view = String.format("%s is %s years old.", student.getName(), student.getAge());
            builder.append(view);

            if (student.getGrade() >= 5.00) {
                builder.append(" Excellent student.");
            } else if (student.getGrade() >= 3.50) {
                builder.append(" Average student.");
            } else {
                builder.append(" Very nice person.");
            }
            System.out.println(builder);
        }
    }
}
