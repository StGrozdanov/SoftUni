package ExamPreparation;

import java.util.Scanner;

public class RageExpenses {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int lostGames = Integer.parseInt(scanner.nextLine());
        double headsetPrice = Double.parseDouble(scanner.nextLine());
        double mousePrice = Double.parseDouble(scanner.nextLine());
        double keyboardPrice = Double.parseDouble(scanner.nextLine());
        double displayPrice = Double.parseDouble(scanner.nextLine());

        double headsetCounter = 0;
        double mouseCounter = 0;
        double keyboardCounter = 0;
        double displayCounter = 0;

        for (int i = 1; i <= lostGames; i++) {
            if (i % 2 == 0){
                headsetCounter++;
            }
            if (i % 3 == 0){
                mouseCounter++;
            }
            if (i % 2 == 0 && i % 3 == 0){
                keyboardCounter++;
            }
        }

        if (keyboardCounter >= 2){
            displayCounter = Math.floor(keyboardCounter / 2);
        }

        double rageExpenses = (headsetCounter * headsetPrice) + (mouseCounter * mousePrice) +
                (keyboardCounter * keyboardPrice) + (displayCounter * displayPrice);

        System.out.printf("Rage expenses: %.2f lv.", rageExpenses);

    }
}
