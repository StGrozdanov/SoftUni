package ObjectsAndClassesExercise10.OpinionPoll;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        List <Person> persons = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String[] tokens = scanner.nextLine().split("\\s+");
            String name = tokens[0];
            int age = Integer.parseInt(tokens[1]);
            Person person = new Person(name, age);
            if (person.getAge() > 30){
                persons.add(person);
            }
        }
        persons.stream().sorted((p1, p2) -> p1.getName().compareTo(p2.getName())).
                forEach(p -> System.out.printf("%s - %d%n", p.getName(), p.getAge()));
    }
}
