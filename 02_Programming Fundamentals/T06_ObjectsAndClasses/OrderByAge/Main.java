package ObjectsAndClassesExercise10.OrderByAge;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        List<Persons> people = new ArrayList<>();

        while (!"End".equals(input)){
            String[] tokens = input.split("\\s+");
            String name = tokens[0];
            String iD = tokens[1];
            int age = Integer.parseInt(tokens[2]);
            Persons persons = new Persons(name, iD, age);
            people.add(persons);

            input = scanner.nextLine();
        }

        people.stream().sorted((p1, p2) -> Integer.compare(p1.getAge(), p2.getAge())).forEach
                (p -> System.out.printf("%s with ID: %s is %d years old.%n", p.getName(), p.getiD(), p.getAge()));

    }
}
