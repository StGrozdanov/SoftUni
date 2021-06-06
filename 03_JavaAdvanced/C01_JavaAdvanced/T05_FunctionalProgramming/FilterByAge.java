package T05_FunctionalProgramming.Lab;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class FilterByAge {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        Map<String, Integer> people = new LinkedHashMap<>();
        for (int i = 0; i < n; i++) {
            String[] tokens = scanner.nextLine().split(", ");
            String name = tokens[0];
            int age = Integer.parseInt(tokens[1]);
            people.put(name, age);
        }
        String criteria = scanner.nextLine();
        int age = Integer.parseInt(scanner.nextLine());
        String outputType = scanner.nextLine();

        Predicate<Integer> tester = createTester(criteria, age);
        Consumer<Map.Entry<String, Integer>> printer = createPrinter(outputType);
        printFilteredPeople(people, tester, printer);


    }

    private static void printFilteredPeople(Map<String, Integer> people, Predicate<Integer> tester, Consumer<Map.Entry<String, Integer>> printer) {
        for (Map.Entry<String, Integer> person : people.entrySet()) {
            if (tester.test(people.get(person.getKey()))){
                printer.accept(person);
            }
        }
    }

    private static Consumer<Map.Entry<String, Integer>> createPrinter(String outputType) {
        Consumer<Map.Entry<String, Integer>> printer = null;

        switch (outputType) {
            case "name age":
                printer = person -> System.out.println(person.getKey() + " - " + person.getValue());
                break;
            case "name":
                printer = person -> System.out.println(person.getKey());
                break;
            case "age":
                printer = person -> System.out.println(person.getValue());
                break;
            case "age name":
                printer = person -> System.out.println(person.getValue() + " - " + person.getKey());
                break;
        }
        return printer;
    }

    private static Predicate<Integer> createTester(String criteria, int age) {
        Predicate<Integer> tester = null;

        switch (criteria) {
            case "older":
                tester = test -> test >= age;
                break;
            case "younger":
                tester = test -> test <= age;
                break;
        }
        return tester;
    }
}
