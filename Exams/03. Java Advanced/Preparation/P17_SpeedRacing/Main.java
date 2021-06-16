package MidExamPreparation.P17_SpeedRacing;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int carsNumber = Integer.parseInt(scanner.nextLine());
        List<Car> cars = new ArrayList<>();
        for (int i = 0; i < carsNumber; i++) {
            String[] tokens = scanner.nextLine().split("\\s+");
            String model = tokens[0];
            double fuelAmount = Double.parseDouble(tokens[1]);
            double fuelCost = Double.parseDouble(tokens[2]);

            Car car = new Car(model, fuelAmount, fuelCost);
            cars.add(car);
        }
        String input = scanner.nextLine();
        while (!"End".equals(input)){
            String[] tokens = input.split("\\s+");
            String model = tokens[1];
            int distance = Integer.parseInt(tokens[2]);
            Car carToDrive = cars.stream().filter(c -> c.getModel().contains(model)).findFirst().orElse(null);
            if (carToDrive != null) {
                carToDrive.Drive(distance);
            }
            input = scanner.nextLine();
        }
        cars.forEach(c -> System.out.println(c));
    }
}
