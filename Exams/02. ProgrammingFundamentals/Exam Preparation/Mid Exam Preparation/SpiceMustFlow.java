package ExamPreparation;

import java.util.Scanner;

public class SpiceMustFlow {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int startingYield = Integer.parseInt(scanner.nextLine());

        int dayCounter = 0;
        int extractedSpice = 0;
        int yield = startingYield;

        while (yield >= 100) {
            dayCounter++;
            extractedSpice += yield - 26;
            yield = startingYield - (dayCounter * 10);
        }
        if (extractedSpice >= 26) {
            extractedSpice -= 26;
        }

        System.out.println(dayCounter);
        System.out.println(extractedSpice);

    }
}
