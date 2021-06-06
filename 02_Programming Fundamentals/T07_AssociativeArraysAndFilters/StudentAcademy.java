package AssociativeArraysFiltersExercise12;

import java.util.*;

public class StudentAcademy {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, List<Double>> studentsGrade = new HashMap<>();

        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < n; i++) {
            String studentName = scanner.nextLine();
            double grade = Double.parseDouble(scanner.nextLine());

            studentsGrade.putIfAbsent(studentName, new ArrayList<>());
            studentsGrade.get(studentName).add(grade);
        }

        studentsGrade.entrySet().stream().filter(g ->
                g.getValue().stream().mapToDouble(Double::doubleValue).average().orElse(0) >= 4.5).
                sorted((g1, g2) -> {
                    double grade1 = g1.getValue().stream().mapToDouble(Double::doubleValue).average().orElse(0);
                    double grade2 = g2.getValue().stream().mapToDouble(Double::doubleValue).average().orElse(0);
                    return Double.compare(grade2, grade1);
                }).forEach(s -> System.out.printf("%s -> %.2f%n", s.getKey(), s.getValue().
                stream().mapToDouble(Double::doubleValue).average().orElse(0)));
    }
}
