package ListsExercise08;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CardsGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> player1 = Arrays.stream(scanner.nextLine().split("\\s+")).map(Integer::parseInt).collect(Collectors.toList());
        List<Integer> player2 = Arrays.stream(scanner.nextLine().split("\\s+")).map(Integer::parseInt).collect(Collectors.toList());

        for (int i = 0; i < Math.min(player1.size(), player2.size()); i++) {
            if (player1.get(i) > player2.get(i)) {
                player1.add(player2.get(i));
                player1.add(player1.get(i));
                player2.remove(player2.get(i));
                player1.remove(player1.get(i));
                i = -1;
            } else if (player2.get(i) > player1.get(i)) {
                player2.add(player1.get(i));
                player2.add(player2.get(i));
                player1.remove(player1.get(i));
                player2.remove(player2.get(i));
                i = -1;
            } else {
                player1.remove(player1.get(i));
                player2.remove(player2.get(i));
                i = -1;
            }
        }
        int player1Sum = 0;
        int player2Sum = 0;

        for (int sum1 : player1) {
            player1Sum += sum1;
        }
        for (int sum2 : player2) {
            player2Sum += sum2;
        }
        if (player1Sum > player2Sum) {
            System.out.printf("First player wins! Sum: %d", player1Sum);
        } else {
            System.out.printf("Second player wins! Sum: %d", player2Sum);
        }
    }
}