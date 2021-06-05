package SameOlShit;

import java.util.Scanner;

public class Vacation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int groupCount = Integer.parseInt(scanner.nextLine());
        String groupType = scanner.nextLine();
        String dayOfWeek = scanner.nextLine();

        double pricePerPerson = 0;
        double totalPriceDiscount = 1;

        switch (dayOfWeek) {
            case "Friday":
                switch (groupType) {
                    case "Students":
                        pricePerPerson = 8.45;
                        break;
                    case "Business":
                        pricePerPerson = 10.90;
                        break;
                    case "Regular":
                        pricePerPerson = 15;
                        break;
                }
                break;
            case "Saturday":
                switch (groupType) {
                    case "Students":
                        pricePerPerson = 9.80;
                        break;
                    case "Business":
                        pricePerPerson = 15.60;
                        break;
                    case "Regular":
                        pricePerPerson = 20;
                        break;
                }
                break;
            case "Sunday":
                switch (groupType) {
                    case "Students":
                        pricePerPerson = 10.46;
                        break;
                    case "Business":
                        pricePerPerson = 16;
                        break;
                    case "Regular":
                        pricePerPerson = 22.5;
                        break;
                }
                break;
        }
        if ("Students".equals(groupType) && groupCount >= 30){
            totalPriceDiscount = 0.85;
        } else if ("Business".equals(groupType) && groupCount >= 100){
            groupCount = groupCount - 10;
        } else if ("Regular".equals(groupType) && groupCount >= 10 && groupCount <= 20){
            totalPriceDiscount = 0.95;
        }

        double totalPrice = (pricePerPerson *groupCount) * totalPriceDiscount;

        System.out.printf("Total price: %.2f", totalPrice);
        }
    }
