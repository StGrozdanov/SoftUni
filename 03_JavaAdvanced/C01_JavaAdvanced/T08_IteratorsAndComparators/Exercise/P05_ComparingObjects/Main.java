package T08_IteratorsAndComparators.Exercise.P05_ComparingObjects;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        List<Person> people = new ArrayList<>();

        while (!"END".equals(input)) {
            String[] tokens = input.split("\\s+");
            String name = tokens[0];
            int age = Integer.parseInt(tokens[1]);
            String town = tokens[2];
            Person person = new Person(name, age, town);
            people.add(person);

            input = scanner.nextLine();
        }

        int equalCounter = 0;
        int diffCounter = 0;
        int personNumber = Integer.parseInt(scanner.nextLine());

        for (Person otherPerson : people) {
            if (people.get(personNumber - 1).compareTo(otherPerson) == 0) {
                equalCounter++;
            } else {
                diffCounter++;
            }
        }

        if (equalCounter > 1) {
            System.out.printf("%d %d %d%n", equalCounter, diffCounter, people.size());
        } else {
            System.out.println("No matches");
        }

    }
}
