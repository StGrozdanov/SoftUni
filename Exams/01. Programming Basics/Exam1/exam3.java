package BasicsExamTest1;

import java.util.Scanner;

public class exam3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String month = scanner.nextLine();
        int hours = Integer.parseInt(scanner.nextLine());
        int people = Integer.parseInt(scanner.nextLine());
        String dayOrNight = scanner.nextLine();

        double pricePerHour = 0;

        switch(month){
            case "march":
            case "april":
            case "may":
                if (dayOrNight.equals("day")){
                    pricePerHour = 10.50;

                } else if (dayOrNight.equals("night")){
                    pricePerHour = 8.40;
            }
                break;
            case "june":
            case "july":
            case "august":
                if (dayOrNight.equals("day")){
                    pricePerHour = 12.60;

                } else if (dayOrNight.equals("night")){
                    pricePerHour = 10.20;
                }
                break;
        }
            if (people >= 4){
                pricePerHour = pricePerHour * 0.9;
            } if (hours >= 5) {
            pricePerHour = pricePerHour * 0.5;
        }
            double pricePerPersonPerHour = pricePerHour;
            double totalPriceVisit = people * hours * pricePerPersonPerHour;

        System.out.printf("Price per person for one hour: %.2f%n", pricePerPersonPerHour);
        System.out.printf("Total cost of the visit: %.2f%n", totalPriceVisit);
    }
}
