package BasicsExamTest1;

import java.util.Scanner;

public class exam1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double pricePerPage = Double.parseDouble(scanner.nextLine());
        double pricePerCover = Double.parseDouble(scanner.nextLine());
        int discount = Integer.parseInt(scanner.nextLine());
        double pricePerDesigner = Double.parseDouble(scanner.nextLine());
        int teamPercentage = Integer.parseInt(scanner.nextLine());

        double startingSum = (pricePerPage * 899) + (pricePerCover * 2);
        double discountSum = startingSum - (startingSum * discount) / 100;
        double priceAfterDesigner = discountSum + pricePerDesigner;
        double finalPrice = priceAfterDesigner - (priceAfterDesigner * teamPercentage) / 100;

        System.out.printf("Avtonom should pay %.2f BGN.", finalPrice);

    }
}
