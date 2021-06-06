package ObjectsAndClassesExercise10.VehicleCatalogue;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        List<Car> vehicles = new ArrayList<>();

        while (!"End".equals(input)){
            String[] tokens = input.split("\\s+");
            String type = tokens[0];
            String model = tokens[1];
            String color = tokens[2];
            int horsePower = Integer.parseInt(tokens[3]);
            Car vehicle = new Car(type, model, color, horsePower);

            vehicles.add(vehicle);

            input = scanner.nextLine();
        }

        int carCounter = 0;
        int truckCounter = 0;
        int truckHpSum = 0;
        int carHpSum = 0;

        for (int i = 0; i < vehicles.size(); i++) {
            if ("car".equals(vehicles.get(i).getType())) {
                carCounter++;
                carHpSum += vehicles.get(i).getHorsePower();
            } else if ("truck".equals(vehicles.get(i).getType())) {
                truckCounter++;
                truckHpSum += vehicles.get(i).getHorsePower();
            }
        }

        double carHp;
        double truckHp;

        if (carCounter == 0){
            carHp = 0;
        } else {
            carHp = 1.0 * carHpSum / carCounter;
        }
        if (truckCounter == 0) {
            truckHp = 0;
        } else {
            truckHp = 1.0 * truckHpSum / truckCounter;
        }

        String model = scanner.nextLine();

        while(!"Close the Catalogue".equals(model)) {
            findVehicleSpecification(vehicles, model);
            model = scanner.nextLine();
        }
            System.out.printf("Cars have average horsepower of: %.2f.%n", carHp);
            System.out.printf("Trucks have average horsepower of: %.2f.%n", truckHp);
    }

    private static void findVehicleSpecification(List<Car> vehicles, String model) {

        for (int i = 0; i < vehicles.size(); i++) {
            if (model.equals(vehicles.get(i).getModel())){
                if ("car".equals(vehicles.get(i).getType())) {
                    System.out.println("Type: Car");
                } else if ("truck".equals(vehicles.get(i).getType())) {
                    System.out.println("Type: Truck");
                }
                System.out.println("Model: " + vehicles.get(i).getModel());
                System.out.println("Color: " + vehicles.get(i).getColor());
                System.out.printf("Horsepower: %.0f%n", vehicles.get(i).getHorsePower());
            }
        }
    }
}
