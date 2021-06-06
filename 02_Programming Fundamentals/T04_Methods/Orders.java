package Methods05;

import java.util.Scanner;

public class Orders {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String product = scanner.nextLine();
        int quantity = Integer.parseInt(scanner.nextLine());

        totalPrice(product, quantity);

    }



    public static void totalPrice(String product, int quantity){
        double result;
        switch(product){
            case "coffee":
                result = quantity * 1.50;
                System.out.printf("%.2f", result);
                break;
            case "water":
                result = quantity * 1.00;
                System.out.printf("%.2f", result);
                break;
            case "coke":
                result = quantity * 1.40;
                System.out.printf("%.2f", result);
                break;
            case "snacks":
                result = quantity * 2.00;
                System.out.printf("%.2f", result);
                break;
        }
    }
}
