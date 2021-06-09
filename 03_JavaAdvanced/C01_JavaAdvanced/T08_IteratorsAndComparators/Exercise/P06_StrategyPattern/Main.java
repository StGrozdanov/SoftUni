package T08_IteratorsAndComparators.Exercise.P06_StrategyPattern;

import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        Set<Person> criteriaOne = new TreeSet<>(new ComparatorByName());
        Set<Person> criteriaTwo = new TreeSet<>(new ComparatorByAge());

        for (int i = 0; i < n; i++) {
            String[] tokens = scanner.nextLine().split("\\s+");
            String name = tokens[0];
            int age = Integer.parseInt(tokens[1]);
            Person person = new Person(name, age);
            criteriaOne.add(person);
            criteriaTwo.add(person);
        }

        criteriaOne.forEach(p -> System.out.printf("%s %d%n", p.getName(), p.getAge()));
        criteriaTwo.forEach(p -> System.out.printf("%s %d%n", p.getName(), p.getAge()));
    }
}
