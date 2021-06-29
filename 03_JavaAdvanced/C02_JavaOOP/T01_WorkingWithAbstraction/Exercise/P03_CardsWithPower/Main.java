package T01_WorkingWithAbstraction.Exercise.P03_CardsWithPower;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String cardRank = scanner.nextLine();
        String cardSuit = scanner.nextLine();

        Card card = new Card(cardRank, cardSuit);

        System.out.printf("Card name: %s of %s; Card power: %d", card.getRankName(), card.getSuitName(),
                card.calculateCardPower());
    }
}
