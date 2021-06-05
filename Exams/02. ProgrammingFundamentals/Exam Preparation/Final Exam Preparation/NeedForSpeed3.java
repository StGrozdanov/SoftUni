package FundamentalsFinalExamPreparation;

import java.util.*;

public class NeedForSpeed3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        Map<String, List<Integer>> carCollection = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            String[] cars = scanner.nextLine().split("\\|");
            String carName = cars[0];
            int mileage = Integer.parseInt(cars[1]);
            int fuel = Integer.parseInt(cars[2]);
            carCollection.put(carName, new ArrayList<>());
            carCollection.get(carName).add(mileage);
            carCollection.get(carName).add(fuel);
        }
        String input = scanner.nextLine();
        while (!"Stop".equals(input)) {
            String[] tokens = input.split(" : ");
            String cmd = tokens[0];
            String car = tokens[1];
            switch (cmd) {
                // mileage = get(0); fuel = get(1);
                case "Drive":
                    int distance = Integer.parseInt(tokens[2]);
                    int requiredFuel = Integer.parseInt(tokens[3]);
                    int availableFuel = carCollection.get(car).get(1);
                    int currentMileage = carCollection.get(car).get(0);
                    if (availableFuel < requiredFuel) {
                        System.out.println("Not enough fuel to make that ride");
                    } else {
                        currentMileage += distance;
                        availableFuel -= requiredFuel;
                        carCollection.get(car).set(0, currentMileage);
                        carCollection.get(car).set(1, availableFuel);
                        System.out.printf("%s driven for %d kilometers. %d liters of fuel consumed.%n",
                                car, distance, requiredFuel);
                    }
                    if (currentMileage >= 100000) {
                        System.out.printf("Time to sell the %s!%n", car);
                        carCollection.remove(car);
                    }
                    break;
                case "Refuel":
                    int refuel = Integer.parseInt(tokens[2]);
                    int fuelCap = 75;
                    int currentFuel = carCollection.get(car).get(1);
                    int amountToAdd;
                    if (currentFuel + refuel > fuelCap) {
                        amountToAdd = fuelCap - currentFuel;
                        carCollection.get(car).set(1, 75);
                    } else {
                        amountToAdd = refuel;
                        carCollection.get(car).set(1, (currentFuel + refuel));
                    }
                    System.out.printf("%s refueled with %d liters%n", car, amountToAdd);
                    break;
                case "Revert":
                    int kilometers = Integer.parseInt(tokens[2]);
                    int currentKilometers = carCollection.get(car).get(0);
                    int kilometersCap = 10000;
                    if (currentKilometers - kilometers <= kilometersCap) {
                        carCollection.get(car).set(0, 10000);
                    } else {
                        int newMileage = currentKilometers - kilometers;
                        carCollection.get(car).set(0, newMileage);
                        System.out.printf("%s mileage decreased by %d kilometers%n", car, kilometers);
                    }
                    break;
            }
            input = scanner.nextLine();
        }
        carCollection.entrySet().stream().sorted((c1, c2) -> c2.getValue().get(0).compareTo(c1.getValue().get(0)))
        .forEach(car -> {
            System.out.printf("%s -> Mileage: %d kms, Fuel in the tank: %d lt.%n", car.getKey(), car.getValue().get(0),
                    car.getValue().get(1));
        });
    }
}
