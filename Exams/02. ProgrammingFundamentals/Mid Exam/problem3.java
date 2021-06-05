package midExam;

import java.util.*;
import java.util.stream.Collectors;

public class problem3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> deck = Arrays.stream(scanner.nextLine().split(":")).collect(Collectors.toList());
        List<String> newDeck = new ArrayList<>();

        String input = scanner.nextLine();

        while (!"Ready".equals(input)) {
            String[] tokens = input.split("\\s+");
            String cmd = tokens[0];

            switch (cmd) {
                case "Add":
                    String cardToAdd = tokens[1];
                    if (deck.contains(cardToAdd)) {
                        newDeck.add(cardToAdd);
                    } else {
                        System.out.println("Card not found.");
                    }
                    break;
                case "Insert":
                    String cardToInsert = tokens[1];
                    int index = Integer.parseInt(tokens[2]);

                    if (index >= 0 && index < deck.size() && deck.contains(cardToInsert)) {
                        newDeck.add(index, cardToInsert);
                    } else {
                        System.out.println("Error!");
                    }

                    break;
                case "Remove":
                    String cardToRemove = tokens[1];

                    if (newDeck.contains(cardToRemove)){
                        newDeck.remove(cardToRemove);
                    } else {
                        System.out.println("Card not found.");
                    }
                    break;
                case "Swap":
                    String card1 = tokens[1];
                    String card2 = tokens[2];
                    int card1Index = newDeck.indexOf(card1);
                    int card2Index = newDeck.indexOf(card2);

                    newDeck.set(card1Index, card2);
                    newDeck.set(card2Index, card1);

                    break;
                case "Shuffle":
                    Collections.reverse(newDeck);
                    break;
            }

            input = scanner.nextLine();
        }
        System.out.println(String.join(" ", newDeck));
    }
}
