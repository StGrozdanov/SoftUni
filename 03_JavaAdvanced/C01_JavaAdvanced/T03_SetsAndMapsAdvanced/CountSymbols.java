package T03_SetsAndMapsAdvanced.Exercise;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class CountSymbols {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        Map<Character, Integer> occurrence = new TreeMap<>();

        for (int i = 0; i < input.length(); i++) {
            char current = input.charAt(i);
            occurrence.putIfAbsent(current, 0);
            occurrence.put(current, occurrence.get(current) + 1);
        }
        occurrence.forEach((key,value) -> System.out.printf("%s: %d time/s%n", key, value));
    }
}
