package AssociativeArraysFiltersExercise12;

import java.util.*;
import java.util.stream.Collectors;

public class CountCharsInString {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> words = Arrays.stream(scanner.nextLine().split("\\s+")).collect(Collectors.toList());
        Map<String, Integer> container = new LinkedHashMap<>();

        for (String word : words) {
            String[] text = word.split("");
            for (String s : text) {
                container.putIfAbsent(s, 0);
                container.put(s, container.get(s) + 1);
            }
        }
        for (Map.Entry<String, Integer> entry : container.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }
}
