package T06_DefiningClasses.Exercise.RawData;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, Car> cars = new LinkedHashMap<>();
        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < n; i++) {

            String[] tokens = scanner.nextLine().split("\\s+");
            String model = tokens[0];
            int engineSpeed = Integer.parseInt(tokens[1]);
            int enginePower = Integer.parseInt(tokens[2]);
            Engine engine = new Engine(engineSpeed, enginePower);
            int cargoWeight = Integer.parseInt(tokens[3]);
            String cargoType = tokens[4];
            Cargo cargo = new Cargo(cargoWeight, cargoType);
            Tire[] tire = new Tire[4];
            int counter = 0;
            for (int j = 5; j < tokens.length; j += 2) {
                double tirePressure = Double.parseDouble(tokens[j]);
                int tireAge = Integer.parseInt(tokens[j + 1]);
                tire[counter] = new Tire(tirePressure, tireAge);
                counter++;
            }
            Car car = new Car(model, engine, cargo, tire);
            cars.putIfAbsent(model, car);
        }
        String input = scanner.nextLine();
        switch (input) {
            case "fragile":
                for (Car car : cars.values()) {
                    if (car.getCargo().getCargoType().equals("fragile")) {
                        boolean correctPressure = false;
                        for (Tire tire : car.getTire()) {
                            if (tire.getPressure() < 1) {
                                correctPressure = true;
                                break;
                            }
                        } // ЦЯЛАТА ТАЗИ ГЛУПОСТ Е ПО-ПРАВИЛНО ДА СЕ ЗАПИШЕ, КАТО МЕТОД В КЛАСА CAR !!!!!!
                        if (correctPressure) {
                            System.out.println(car.getModel());
                        }
                    }
                }
                break;
            case "flamable":
                for (Car car : cars.values()) {
                    if (car.getCargo().getCargoType().equals("flamable") && car.getEngine().getEnginePower() > 250) {
                        System.out.println(car.getModel());
                    }
                }
                break;
        }

    }
}
