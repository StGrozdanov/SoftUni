package AssociativeArraysFiltersExercise12;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class SoftUniParking {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        Map<String, String> system = new LinkedHashMap<>();

        for (int i = 0; i < n; i++) {
            String[] tokens = scanner.nextLine().split("\\s+");
            String cmd = tokens[0];
            String name = tokens[1];
            switch (cmd) {
                case "register":
                    String licensePlate = tokens[2];
                    if (!system.containsKey(name)) {
                        system.put(name, licensePlate);
                        System.out.printf("%s registered %s successfully%n", name, licensePlate);
                    } else if (system.containsKey(name)) {
                        System.out.printf("ERROR: already registered with plate number %s%n", system.get(name));
                    }
                    break;
                case "unregister":
                    if (!system.containsKey(name)) {
                        System.out.printf("ERROR: user %s not found%n", name);
                    } else if (system.containsKey(name)) {
                        system.remove(name);
                        System.out.printf("%s unregistered successfully%n", name);
                    }
                    break;
            }
        }
        system.forEach((key, value) -> System.out.printf("%s => %s%n", key, value));
    }
}
