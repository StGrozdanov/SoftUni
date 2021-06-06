package AssociativeArraysFilters11;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

public class CountRealNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Double> input = Arrays.stream(scanner.nextLine().split(" ")).map(Double::parseDouble).
                collect(Collectors.toList());

        Map<Double, Integer> occurrences = new TreeMap<>();

        for (Double inputs : input) {
            if (!occurrences.containsKey(inputs)) {
                occurrences.put(inputs, 0);
            }
                occurrences.put(inputs, occurrences.get(inputs) + 1);
        }
        for(Map.Entry<Double, Integer> entry:occurrences.entrySet()){
            DecimalFormat rule = new DecimalFormat("#.#######");
            System.out.println(rule.format(entry.getKey()) + " -> " + entry.getValue());
        }
    }
}
