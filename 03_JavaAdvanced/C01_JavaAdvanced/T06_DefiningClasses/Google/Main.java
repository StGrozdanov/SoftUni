package T06_DefiningClasses.Exercise.Google;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, Person> people = new LinkedHashMap<>();
        String input = scanner.nextLine();

        while (!"End".equals(input)) {
            String[] tokens = input.split("\\s+");
            String name = tokens[0];
            String information = tokens[1];
            people.putIfAbsent(name, new Person(name));
            switch (information) {
                case "company":
                    String companyName = tokens[2];
                    String department = tokens[3];
                    double salary = Double.parseDouble(tokens[4]);
                    Company company = new Company(companyName, department, salary);
                    people.get(name).setCompany(company);
                    break;
                case "pokemon":
                    String pokemonName = tokens[2];
                    String pokemonType = tokens[3];
                    Pokemon pokemon = new Pokemon(pokemonName, pokemonType);
                    people.get(name).addPokemons(pokemon);
                    break;
                case "parents":
                    String parentName = tokens[2];
                    String parentBirthday = tokens[3];
                    Parents parents = new Parents(parentName, parentBirthday);
                    people.get(name).addParents(parents);
                    break;
                case "children":
                    String childName = tokens[2];
                    String childBirthday = tokens[3];
                    Children children = new Children(childName, childBirthday);
                    people.get(name).addChildren(children);
                    break;
                case "car":
                    String model = tokens[2];
                    String speed = tokens[3];
                    Car car = new Car(model, speed);
                    people.get(name).setCar(car);
                    break;
            }
            input = scanner.nextLine();
        }
        input = scanner.nextLine();

        System.out.println(people.get(input).toString());
    }
}
