package T03_SetsAndMapsAdvanced.Lab;

import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class ParkingLot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        Set<String> parking = new LinkedHashSet<>();

        while (!"END".equals(input)) {
            String[] tokens = input.split(",\\s+");
            String direction = tokens[0];
            String licensePlate = tokens[1];

            switch (direction) {
                case "IN":
                    parking.add(licensePlate);
                    break;
                case "OUT":
                    parking.remove(licensePlate);
                    break;
            }
            input = scanner.nextLine();
        }
        if (parking.isEmpty()) {
            System.out.println("Parking Lot is Empty");
        } else {
            for (String s : parking) {
                System.out.println(s);
            }
        }
    }
}
