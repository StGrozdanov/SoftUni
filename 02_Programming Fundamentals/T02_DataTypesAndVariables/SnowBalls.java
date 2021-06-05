package DataTypesAndVariables;

import java.util.Scanner;

public class SnowBalls {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numberOfSnowballs = Integer.parseInt(scanner.nextLine());

        double bestValue = 0;
        int bestSnowballsNow = 0;
        int bestSnowballTime = 0;
        int bestSnowballQuality = 0;

        for (int i = 0; i < numberOfSnowballs; i++){
            int snowballsNow = Integer.parseInt(scanner.nextLine());
            int snowballTime = Integer.parseInt(scanner.nextLine());
            int snowballQuality = Integer.parseInt(scanner.nextLine());

            double snowballValue = Math.pow((1.0 * snowballsNow / snowballTime), snowballQuality);

            if (snowballValue > bestValue){
                bestValue = snowballValue;
                bestSnowballsNow = snowballsNow;
                bestSnowballTime = snowballTime;
                bestSnowballQuality = snowballQuality;
            }

        }

        System.out.printf("%d : %d = %.0f (%d)", bestSnowballsNow, bestSnowballTime, bestValue, bestSnowballQuality);
    }
}
