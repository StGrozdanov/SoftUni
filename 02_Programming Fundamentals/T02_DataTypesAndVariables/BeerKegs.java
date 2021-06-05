package DataTypesAndVariables;

import java.util.Scanner;

public class BeerKegs {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int input = Integer.parseInt(scanner.nextLine());

        double biggestSize = 0;
        double currentSize = 0;
        String biggestKeg = "";

        for (int i = 0; i < input; i++){
            String model = scanner.nextLine();
            double radius = Double.parseDouble(scanner.nextLine());
            int height = Integer.parseInt(scanner.nextLine());

            currentSize = Math.PI * Math.pow(radius, 2) * height;

            if (currentSize > biggestSize){
                biggestSize = currentSize;
                biggestKeg = model;
            }
            currentSize = 0;
        }
        System.out.printf("%s", biggestKeg);
    }
}
