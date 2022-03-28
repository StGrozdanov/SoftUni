package ProgrammingBasics.AdvancedConditionalStatements.AdvConditionsExercise;

import java.util.Scanner;

public class HotelRoom {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String month = scanner.nextLine();
        int days = Integer.parseInt(scanner.nextLine());

        double apartmentPrice = 0;
        double studioPrice = 0;

        switch (month){
            case "May":
            case "October":
                apartmentPrice = 65;
                studioPrice = 50;
                if (days > 14){
                    studioPrice = studioPrice * 0.7;
                } else if (days > 7){
                    studioPrice = studioPrice * 0.95;
                }
                break;
            case "June":
            case "September":
                apartmentPrice = 68.70;
                studioPrice = 75.20;
                if (days > 14){
                    studioPrice = studioPrice * 0.8;
            }
                break;
            case "July":
            case "August":
                apartmentPrice = 77;
                studioPrice = 76;
                break;
        } if (days > 14){
            apartmentPrice = apartmentPrice * 0.9;
        } double apartmentTotalPrice = apartmentPrice * days;
          double studioTotalPrice = studioPrice * days;

        System.out.printf("Apartment: %.2f lv.%n", apartmentTotalPrice);
        System.out.printf("Studio: %.2f lv.",studioTotalPrice);
    }
}
