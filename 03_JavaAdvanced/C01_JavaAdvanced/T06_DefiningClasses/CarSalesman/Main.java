package T06_DefiningClasses.Exercise.CarSalesman;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Car> cars = new ArrayList<>();
        List<Engine> engines = new ArrayList<>();
        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < n; i++) {
            String[] tokens = scanner.nextLine().split("\\s+");
            String model = tokens[0];
            int power = Integer.parseInt(tokens[1]);
            Engine engine;
            if (tokens.length == 2) {
                engine = new Engine(model, power);
            } else if (tokens.length == 4) {
                String displacement = tokens[2];
                String efficiency = tokens[3];
                engine = new Engine(model, power, displacement, efficiency);
            } else {
                if (tokens[2].matches("^\\d+$")) {
                    String displacement = tokens[2];
                    engine = new Engine(model, power, displacement);
                } else {
                    String efficiency = tokens[2];
                    engine = new Engine(model, efficiency, power);
                }
            }
            engines.add(engine);
        }

        int m = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < m; i++) {
            String[] tokens = scanner.nextLine().split("\\s+");
            String carModel = tokens[0];
            String engineModel = tokens[1];
            Car car = null;
            if (tokens.length == 2) {
                for (Engine engine : engines) {
                    if (engine.getModel().equals(engineModel)) {
                        car = new Car(carModel, engine);
                    }
                }
            } else if (tokens.length == 4) {
                String weight = tokens[2];
                String color = tokens[3];
                for (Engine engine : engines) {
                    if (engine.getModel().equals(engineModel)) {
                        car = new Car(carModel, engine, weight, color);
                    }
                }
            } else {
                if (tokens[2].matches("^\\d+$")) {
                    String weight = tokens[2];
                    for (Engine engine : engines) {
                        if (engine.getModel().equals(engineModel)) {
                            car = new Car(carModel, engine, weight);
                        }
                    }
                } else {
                    String color = tokens[2];
                    for (Engine engine : engines) {
                        if (engine.getModel().equals(engineModel)) {
                            car = new Car(carModel, color, engine);
                        }
                    }
                }
            }
            cars.add(car);
        }
        cars.forEach(x -> System.out.println(x.toString()));
    }
}
