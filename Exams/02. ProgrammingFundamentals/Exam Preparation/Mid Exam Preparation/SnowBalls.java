package ExamPreparation;

import java.util.Scanner;

public class SnowBalls {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        double currentSnowballQuality = 0;
        double bestSnowballQuality = 0;
        double bestSnowBallSnow = 0;
        double bestSnowBallTime = 0;
        double bestSnowBallQ = 0;


        for (int i = 1; i <= n; i++) {
            double snowBallSnow = Double.parseDouble(scanner.nextLine());
            double snowBallTime = Double.parseDouble(scanner.nextLine());
            double snowBallQuality = Double.parseDouble(scanner.nextLine());

            currentSnowballQuality = Math.pow(( snowBallSnow / snowBallTime), snowBallQuality);

            if (currentSnowballQuality > bestSnowballQuality){
                bestSnowballQuality = currentSnowballQuality;
                bestSnowBallQ = snowBallQuality;
                bestSnowBallSnow = snowBallSnow;
                bestSnowBallTime = snowBallTime;
            }

        }
        System.out.printf("%.0f : %.0f = %.0f (%.0f)", bestSnowBallSnow, bestSnowBallTime, bestSnowballQuality, bestSnowBallQ);
    }
}
