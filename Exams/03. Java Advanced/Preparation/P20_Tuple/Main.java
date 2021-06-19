package MidExamPreparation.P20_Tuple;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < 3; i++) {
            String[] tokens = scanner.nextLine().split("\\s+");
            if (i == 0) {
                String firstName = tokens[0];
                String lastName = tokens[1];
                String address = tokens[2];
                String fullName = firstName + " " + lastName;
                Tuple<String, String> nameAddress = new Tuple<>(fullName, address);
                System.out.println(nameAddress);
            } else if (i == 1) {
                String firstName = tokens[0];
                int litersOfBeer = Integer.parseInt(tokens[1]);
                Tuple<String, Integer> beerData = new Tuple<>(firstName, litersOfBeer);
                System.out.println(beerData);
            } else {
                int number = Integer.parseInt(tokens[0]);
                double dec = Double.parseDouble(tokens[1]);
                Tuple<Integer, Double> numbers = new Tuple<>(number, dec);
                System.out.println(numbers);
            }
        }
    }
}
