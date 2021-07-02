package Т05_Polymorphism.Exercise.P02_VehiclesExtended;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] tokens = scanner.nextLine().split("\\s+");
        Car car = car(tokens);
        tokens = scanner.nextLine().split("\\s+");
        Truck truck = truck(tokens);
        tokens = scanner.nextLine().split("\\s+");
        Bus bus = bus(tokens);

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
                    } else if (vehicle.equals("Т05_Polymorphism.Exercise.P02_VehiclesExtended.Bus")) {
                        bus.drive(Double.parseDouble(cmdArgs[2]));
                    }
                    break;
                case "Refuel":
                    if (vehicle.equals("Т05_Polymorphism.Exercise.P02_VehiclesExtended.Car")) {
                        car.refuel(Double.parseDouble(cmdArgs[2]));
                    } else if (vehicle.equals("Т05_Polymorphism.Exercise.P02_VehiclesExtended.Truck")) {
                        truck.refuel(Double.parseDouble(cmdArgs[2]));
                    } else if (vehicle.equals("Т05_Polymorphism.Exercise.P02_VehiclesExtended.Bus")) {
                        bus.refuel(Double.parseDouble(cmdArgs[2]));
                    }
                    break;
                case "DriveEmpty":
                    bus.driveEmpty(Double.parseDouble(cmdArgs[2]));
                    break;
            }
        }
        System.out.println(car);
        System.out.println(truck);
        System.out.println(bus);
    }

    public static Truck truck(String[] tokens) {
        double fuelQuantity = Double.parseDouble(tokens[1]);
        double litersPerKm = Double.parseDouble(tokens[2]);
        double tankCapacity = Double.parseDouble(tokens[3]);

        return new Truck(fuelQuantity, litersPerKm, tankCapacity);
    }

    public static Car car(String[] tokens) {
        double fuelQuantity = Double.parseDouble(tokens[1]);
        double litersPerKm = Double.parseDouble(tokens[2]);
        double tankCapacity = Double.parseDouble(tokens[3]);

        return new Car(fuelQuantity, litersPerKm, tankCapacity);
    }

    public static Bus bus(String[] tokens) {
        double fuelQuantity = Double.parseDouble(tokens[1]);
        double litersPerKm = Double.parseDouble(tokens[2]);
        double tankCapacity = Double.parseDouble(tokens[3]);

        return new Bus(fuelQuantity, litersPerKm, tankCapacity);
    }

}
