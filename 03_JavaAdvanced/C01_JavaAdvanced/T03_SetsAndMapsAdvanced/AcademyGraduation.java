package T03_SetsAndMapsAdvanced.Lab;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class AcademyGraduation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        Map<String, Double> scores = new TreeMap<>();

        for (int i = 0; i < n; i++) {
            String student = scanner.nextLine();
            double grade = Arrays.stream(scanner.nextLine().split("\\s+"))
                    .mapToDouble(Double::parseDouble).average().getAsDouble();
            scores.putIfAbsent(student, grade);
        }
        scores.forEach((key, value) -> System.out.printf("%s is graduated with %s%n", key,
                new DecimalFormat("0.####").format(value)));
    }
}
