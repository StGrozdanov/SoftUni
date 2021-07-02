package Т05_Polymorphism.Exercise.P01_Vehicles;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] tokens = scanner.nextLine().split("\\s+");
        Car car = car(tokens);
        tokens = scanner.nextLine().split("\\s+");
        Truck truck = truck(tokens);

        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < n; i++) {
            String[] cmdArgs = scanner.nextLine().split("\\s+");
            String cmd = cmdArgs[0];
            String vehicle = cmdArgs[1];
            switch (cmd) {
                case "Drive":
                    if (vehicle.equals("Т05_Polymorphism.Exercise.P02_VehiclesExtended.Car")) {
                        car.drive(Double.parseDouble(cmdArgs[2]));
                    } else if (vehicle.equals("Т05_Polymorphism.Exercise.P02_VehiclesExtended.Truck")) {
                        truck.drive(Double.parseDouble(cmdArgs[2]));
                    }
                    break;
                case "Refuel":
                    if (vehicle.equals("Т05_Polymorphism.Exercise.P02_VehiclesExtended.Car")) {
                        car.refuel(Double.parseDouble(cmdArgs[2]));
                    } else if (vehicle.equals("Т05_Polymorphism.Exercise.P02_VehiclesExtended.Truck")) {
                        truck.refuel(Double.parseDouble(cmdArgs[2]));
                    }
                    break;
            }
        }
        System.out.println(car);
        System.out.println(truck);
    }

    public static Truck truck (String[] tokens){
        double fuelQuantity = Double.parseDouble(tokens[1]);
        double litersPerKm = Double.parseDouble(tokens[2]);

        return new Truck(fuelQuantity, litersPerKm);
    }
    public static Car car (String[] tokens){
        double fuelQuantity = Double.parseDouble(tokens[1]);
        double litersPerKm = Double.parseDouble(tokens[2]);

        return new Car(fuelQuantity, litersPerKm);
    }

}
