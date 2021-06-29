package T01_WorkingWithAbstraction.Lab.P04_HotelReservation;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] input = scanner.nextLine().split("\\s+");

        double pricePerDay = Double.parseDouble(input[0]);
        int numberOfDays = Integer.parseInt(input[1]);
        Season season = Season.valueOf(input[2].toUpperCase());
        DiscountType discountType = DiscountType.valueOf(input[3].toUpperCase());

        PriceCalculator calculator = new PriceCalculator(pricePerDay, numberOfDays, season, discountType);

        System.out.printf("%.2f", calculator.calculate());
    }
}
