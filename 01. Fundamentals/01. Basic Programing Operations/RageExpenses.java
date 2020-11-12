package SameOlShit;

import java.util.Scanner;

public class RageExpenses {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int lostGames = Integer.parseInt(scanner.nextLine());
        double headsetPrice = Double.parseDouble(scanner.nextLine());
        double mousePrice = Double.parseDouble(scanner.nextLine());
        double keyboardPrice = Double.parseDouble(scanner.nextLine());
        double displayPrice = Double.parseDouble(scanner.nextLine());

        int keyboardCounter = 0;

        double headsetTrashed = Math.floor(1.0 * lostGames / 2);
        double mouseTrashed = Math.floor(1.0 * lostGames / 3);

        for (int i = 1; i <= lostGames; i++) {
            if (i % 2 == 0 && i % 3 == 0) {
                keyboardCounter++;
            }
        }
        double displayCounter = Math.floor(1.0 * keyboardCounter / 2);
        double total = (headsetTrashed * headsetPrice) + (mouseTrashed * mousePrice) + (keyboardCounter * keyboardPrice) + (displayCounter * displayPrice);

        System.out.printf("Rage expenses: %.2f lv.", total);
    }
}
