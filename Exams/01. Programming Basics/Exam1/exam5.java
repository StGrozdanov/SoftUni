package BasicsExamTest1;

import java.util.Scanner;

public class exam5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String type = scanner.nextLine();

        int kidCounter = 0;
        int adultCounter = 0;
        int toysPrice = 0;
        int clothesPrice = 0;

        while (!type.equals("Christmas")){
            int age = Integer.parseInt(type);
            if (age <= 16){
                kidCounter++;
                toysPrice = kidCounter * 5;
            } else {
                adultCounter++;
                clothesPrice = adultCounter * 15;
            }
            type = scanner.nextLine();
        }
        System.out.printf("Number of adults: %d%n", adultCounter);
        System.out.printf("Number of kids: %d%n", kidCounter);
        System.out.printf("Money for toys: %d%n", toysPrice);
        System.out.printf("Money for sweaters: %d%n", clothesPrice);
    }
}
