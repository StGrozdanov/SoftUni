package AssociativeArraysFilters11;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class WordFilter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Arrays.stream(scanner.nextLine().split("\\s+")).
                filter(n -> n.length() % 2 == 0).
                collect(Collectors.toList()).
                forEach(System.out::println);
    }
}
