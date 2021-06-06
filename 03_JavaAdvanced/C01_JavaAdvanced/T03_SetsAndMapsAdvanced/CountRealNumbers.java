package T03_SetsAndMapsAdvanced.Lab;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class CountRealNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<Double, Integer> numbers = new LinkedHashMap<>();
        double[] tokens = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToDouble(Double::parseDouble)
                .toArray();

        for (double token : tokens) {
            if (!numbers.containsKey(token)) {
                numbers.put(token, 1);
            } else {
                int newValue = numbers.get(token) + 1;
                numbers.put(token, newValue);
            }
        }
        numbers.forEach((key, value) -> System.out.printf("%.1f -> %d%n", key, value));
    }
}
