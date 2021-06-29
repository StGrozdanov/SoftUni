package T01_WorkingWithAbstraction.Exercise.P02_CardRank;

public class Main {
    public static void main(String[] args) {
        System.out.println("Card Ranks:");
        CardRank[] cardRanks = CardRank.values();
        for (CardRank cardRank : cardRanks) {
            System.out.printf("Ordinal value: %d; Name value: %s%n", cardRank.ordinal(), cardRank.name());
        }
    }
}
