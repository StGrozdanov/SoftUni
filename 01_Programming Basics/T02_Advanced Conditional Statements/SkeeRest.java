package ProgrammingBasics.AdvancedConditionalStatements;

import java.util.Scanner;

public class SkeeRest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int days = Integer.parseInt(scanner.nextLine());
        String room = scanner.nextLine();
        String grade = scanner.nextLine();

        double roomForOne = 18.00;
        double apartment = 25.00;
        double presidentApartment = 35.00;
        double sleep = days - 1;
        double endPrice = 0;

        if (sleep < 10){
            apartment = 25 * 0.7;
            presidentApartment = 35 * 0.9;
        } else if (sleep >= 10 && sleep <= 15){
            apartment = 25 * 0.65;
            presidentApartment = 35 * 0.85;
        } else {
            apartment = 25 * 0.5;
            presidentApartment = 35 * 0.8;
        }

        if (room.equals("room for one person")){
            endPrice = roomForOne * sleep;
        } else if (room.equals("apartment")){
            endPrice = apartment * sleep;
        } else if (room.equals("president apartment")){
            endPrice = presidentApartment * sleep;
        }

        if (grade.equals("positive")){
            endPrice = endPrice * 1.25;
        } else if (grade.equals("negative")){
            endPrice = endPrice * 0.9;
        }

        System.out.printf("%.2f", endPrice);
    }
}
