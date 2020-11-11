package ProgrammingBasics.AdvancedConditionalStatements;

import java.util.Scanner;

public class FruitShop {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String fruit = scanner.nextLine();
        String day = scanner.nextLine();
        double quantity = Double.parseDouble(scanner.nextLine());

        double price = 0.0;
        double finalPrice = 0.0;

        if (day.equals("Monday")||day.equals("Tuesday")||day.equals("Wednesday")||day.equals("Thursday")||day.equals("Friday")){
            switch (fruit){
                case "banana":
                    price = 2.50;
                    finalPrice = price * quantity;
                    System.out.printf("%.2f", finalPrice);
                    break;
                case "apple":
                    price = 1.20;
                    finalPrice = price * quantity;
                    System.out.printf("%.2f", finalPrice);
                    break;
                case "orange":
                    price = 0.85;
                    finalPrice = price * quantity;
                    System.out.printf("%.2f", finalPrice);
                    break;
                case "grapefruit":
                    price = 1.45;
                    finalPrice = price * quantity;
                    System.out.printf("%.2f", finalPrice);
                    break;
                case "kiwi":
                    price = 2.70;
                    finalPrice = price * quantity;
                    System.out.printf("%.2f", finalPrice);
                    break;
                case "pineapple":
                    price = 5.50;
                    finalPrice = price * quantity;
                    System.out.printf("%.2f", finalPrice);
                    break;
                case "grapes":
                    price = 3.85;
                    finalPrice = price * quantity;
                    System.out.printf("%.2f", finalPrice);
                    break;
                default:
                    System.out.println("error");
                    break;
            }
        } else if (day.equals("Saturday")||day.equals("Sunday")){
            switch (fruit){
                case "banana":
                    price = 2.70;
                    finalPrice = price * quantity;
                    System.out.printf("%.2f", finalPrice);
                    break;
                case "apple":
                    price = 1.25;
                    finalPrice = price * quantity;
                    System.out.printf("%.2f", finalPrice);
                    break;
                case "orange":
                    price = 0.90;
                    finalPrice = price * quantity;
                    System.out.printf("%.2f", finalPrice);
                    break;
                case "grapefruit":
                    price = 1.60;
                    finalPrice = price * quantity;
                    System.out.printf("%.2f", finalPrice);
                    break;
                case "kiwi":
                    price = 3.00;
                    finalPrice = price * quantity;
                    System.out.printf("%.2f", finalPrice);
                    break;
                case "pineapple":
                    price = 5.60;
                    finalPrice = price * quantity;
                    System.out.printf("%.2f", finalPrice);
                    break;
                case "grapes":
                    price = 4.20;
                    finalPrice = price * quantity;
                    System.out.printf("%.2f", finalPrice);
                    break;
                default:
                    System.out.println("error");
                    break;
            }
        } else {
            System.out.println("error");
        }
    }
}
