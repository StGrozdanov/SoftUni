package DataTypesAndVariables;

import java.util.Scanner;

public class SpiceMustFlow {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int startingYield = Integer.parseInt(scanner.nextLine());

        int dayCounter = 0;
        int extractedSpice = 0;

        while (startingYield >= 100){
            dayCounter++;
            extractedSpice += startingYield - 26;
            startingYield -= 10;
        }
        if (extractedSpice >= 26) {
            extractedSpice -= 26;
        }
        System.out.println(dayCounter);
        System.out.println(extractedSpice);
    }
}
