package T02_Encapsulation.Exercise.P03_ShoppingSpree;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Person> people = new ArrayList<>();
        findPeople(people, scanner);
        List<Product> products = new ArrayList<>();
        findProducts(products, scanner);

        String input = scanner.nextLine();
        while (!"END".equals(input)){
            String[] tokens = input.split("\\s+");
            String name = tokens[0];
            String product = tokens[1];
            Person person = people.stream().filter(p -> p.getName().equals(name)).findFirst().orElse(null);
            Product productToBuy = products.stream().filter(p -> p.getName().equals(product)).findFirst()
                    .orElse(null);
            if (person != null && productToBuy != null) {
                person.buyProduct(productToBuy);
            }
            input = scanner.nextLine();
        }

        System.out.println();
        people.forEach(p -> System.out.println(p));
    }

    private static void findPeople(List<Person> people, Scanner scanner){
        String[] peopleTokens = scanner.nextLine().split(";");
        for (int i = 0; i < peopleTokens.length; i++) {
            String[] tokens = peopleTokens[i].split("=");
            String name = tokens[0];
            double money = Double.parseDouble(tokens[1]);
            Person person = new Person(name, money);
            people.add(person);
        }
    }
    private static void findProducts(List<Product> products, Scanner scanner){
        String[] peopleTokens = scanner.nextLine().split(";");
        for (int i = 0; i < peopleTokens.length; i++) {
            String[] tokens = peopleTokens[i].split("=");
            String name = tokens[0];
            double money = Double.parseDouble(tokens[1]);
            Product product = new Product(name, money);
            products.add(product);
        }
    }
}
