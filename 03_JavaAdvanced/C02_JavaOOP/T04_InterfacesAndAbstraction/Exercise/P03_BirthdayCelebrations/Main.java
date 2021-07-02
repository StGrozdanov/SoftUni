package T04_InterfacesAndAbstraction.Exercise.P03_BirthdayCelebrations;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Birthable> validClasses = new ArrayList<>();
        String input = scanner.nextLine();

        while (!"End".equals(input)) {
            String[] tokens = input.split("\\s+");
            String clazz = tokens[0];
            switch (clazz) {
                case "Citizen":
                    validClasses.add(new Citizen(tokens[1], Integer.parseInt(tokens[2]), tokens[3], tokens[4]));
                    break;
                case "Pet":
                    validClasses.add(new Pet(tokens[1], tokens[2]));
                    break;
            }
            input = scanner.nextLine();
        }
        String targetYear = scanner.nextLine();
        validClasses.stream().filter(p -> p.getBirthDate().contains(targetYear))
                .forEach(p -> System.out.println(p.getBirthDate()));
    }
}
