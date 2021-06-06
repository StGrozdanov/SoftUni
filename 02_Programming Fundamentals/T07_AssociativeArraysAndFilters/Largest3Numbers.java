package AssociativeArraysFilters11;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Largest3Numbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Arrays.stream(scanner.nextLine().split("\\s+")).
                map(Integer::parseInt).
                sorted((n1, n2) -> n2.compareTo(n1)).limit(3).
                collect(Collectors.toList()).forEach(x -> System.out.print(x + " "));

    }
}
