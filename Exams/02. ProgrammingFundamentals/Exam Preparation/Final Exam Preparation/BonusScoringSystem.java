package exam01;

import java.util.Scanner;

public class BonusScoringSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int studentsCount = Integer.parseInt(scanner.nextLine());
        int lecturesCount = Integer.parseInt(scanner.nextLine());
        int initialBonus = Integer.parseInt(scanner.nextLine());

        double biggestBonus = 0;
        int bestLecturesCount = 0;

        for (int i = 1; i <= studentsCount; i++) {
            int attendance = Integer.parseInt(scanner.nextLine());
            double totalBonus = (1.0 * attendance / lecturesCount) * (5 + initialBonus);

            if (totalBonus > biggestBonus) {
                biggestBonus = totalBonus;
                bestLecturesCount = attendance;
            }
        }

        System.out.printf("Max Bonus: %.0f.%n", Math.ceil(biggestBonus));
        System.out.printf("The student has attended %d lectures.", bestLecturesCount);

    }
}
