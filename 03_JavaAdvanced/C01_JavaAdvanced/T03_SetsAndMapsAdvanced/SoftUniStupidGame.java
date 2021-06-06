package T03_SetsAndMapsAdvanced.Lab;

import java.util.*;
import java.util.stream.Collectors;

public class SoftUniStupidGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Set<Integer> player1 = Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(LinkedHashSet::new));
        Set<Integer> player2 = Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(LinkedHashSet::new));

        int rounds = 50;

        while (rounds-- > 0 && !player1.isEmpty() && !player2.isEmpty()){
            int player1Draw = player1.iterator().next();
            player1.remove(player1Draw);
            int player2Draw = player2.iterator().next();
            player2.remove(player2Draw);
            if (player1Draw > player2Draw) {
                player1.add(player1Draw);
                player1.add(player2Draw);
            } else if (player2Draw > player1Draw) {
                player2.add(player1Draw);
                player2.add(player2Draw);
            }
        }
        if (player1.size() == player2.size()) {
            System.out.println("Draw!");
        } else if (player1.size() > player2.size()) {
            System.out.println("First player win!");
        } else {
            System.out.println("Second player win!");
        }
    }
}
