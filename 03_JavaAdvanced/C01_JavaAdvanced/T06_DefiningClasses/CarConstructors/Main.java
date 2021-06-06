package T06_DefiningClasses.Lab.CarConstructors;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int carCount = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < carCount; i++) {
            String[] tokens = scanner.nextLine().split("\\s+");

            if (tokens.length == 3) {
                String brand = tokens[0];
                String model = tokens[1];
                int horsePower = Integer.parseInt(tokens[2]);

                Car car = new Car(brand, model, horsePower);
                System.out.println(car.carInfo());
            } else if (tokens.length == 1) {
                Car car = new Car(tokens[0]);
                System.out.println(car.carInfo());
            }
        }

    }
}
