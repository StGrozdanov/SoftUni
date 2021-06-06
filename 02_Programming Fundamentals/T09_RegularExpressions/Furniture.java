package RegularExpressions;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Furniture {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String expression = ">>(?<furnitureName>[A-Za-z]+)<<(?<price>\\d+([.]\\d+)?)!(?<quantity>\\d+)";
        String input = scanner.nextLine();

        StringBuilder text = new StringBuilder();

        while(!"Purchase".equals(input)){
            text.append(input);
            input = scanner.nextLine();
        }

        Pattern pattern = Pattern.compile(expression);
        Matcher furniture = pattern.matcher(text);

        double totalPrice = 0;
        System.out.println("Bought furniture:");
        while (furniture.find()) {
            String furnitureName = furniture.group("furnitureName");
            int quantity = Integer.parseInt(furniture.group("quantity"));
            double price = Double.parseDouble(furniture.group("price"));
            totalPrice += price * quantity;
            System.out.println(furnitureName);
        }
        System.out.printf("Total money spend: %.2f", totalPrice);
    }
}
