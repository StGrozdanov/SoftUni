package ProgrammingBasics.AdvancedConditionalStatements.AdvConditionsExercise;

import java.util.Scanner;

public class Cinema {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String type = scanner.nextLine();
        int columns = Integer.parseInt(scanner.nextLine());
        int rows = Integer.parseInt(scanner.nextLine());

        double sale = 0.0;

        switch (type){
            case "Premiere":
                sale = columns * rows * 12.00;
                System.out.printf("%.2f leva", sale);
                break;
            case "Normal":
                sale = columns * rows * 7.50;
                System.out.printf("%.2f leva", sale);
                break;
            case "Discount":
                sale = columns * rows * 5.00;
                System.out.printf("%.2f leva", sale);
                break;
        }

    }
}
