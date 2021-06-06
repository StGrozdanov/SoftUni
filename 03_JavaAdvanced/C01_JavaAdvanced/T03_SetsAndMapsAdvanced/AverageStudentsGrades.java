package T03_SetsAndMapsAdvanced.Lab;

import java.util.*;

public class AverageStudentsGrades {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        Map<String, List<Double>> records = new TreeMap<>();

        for (int i = 0; i < n; i++) {
            String[] tokens = scanner.nextLine().split("\\s+");
            String student = tokens[0];
            double grade = Double.parseDouble(tokens[1]);

            records.putIfAbsent(student, new ArrayList<>());
            records.get(student).add(grade);
        }

        List<Double> avgScores = new ArrayList<>();

        for (Map.Entry<String, List<Double>> entry : records.entrySet()) {
            for (List<Double> value : records.values()) {
                double average = value.stream().mapToDouble(Double::doubleValue).average().getAsDouble();
                avgScores.add(average);
            }
        }
        records.forEach((key, value) -> {
            System.out.printf("%s -> ", key);
            value.forEach(z -> System.out.printf("%.2f ", z));
            System.out.printf("(avg: %.2f)%n", avgScores.get(0));
            avgScores.remove(0);
        });
    }
}
