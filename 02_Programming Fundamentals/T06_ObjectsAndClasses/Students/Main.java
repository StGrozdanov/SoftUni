package ObjectsAndClassesExercise10.Students;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        List<Students> student = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String[] tokens = scanner.nextLine().split("\\s+");
            double grade = Double.parseDouble(tokens[2]);
            Students students = new Students(tokens[0], tokens[1], grade);
            student.add(students);
        }

        student.stream().sorted((s1, s2) -> Double.compare(s2.getGrade(), s1.getGrade())).
                forEach(s -> System.out.printf("%s %s: %.2f%n", s.getFirstName(), s.getLastName(), s.getGrade()));
    }
}
