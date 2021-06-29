package T01_WorkingWithAbstraction.Exercise.P03_CardsWithPower;

public class Card {
    private String rankName;
    private String suitName;
    private CardRank cardRank;
    private CardSuit cardSuit;

    public Card(String cardRank, String cardSuit) {
        this.rankName = cardRank;
        this.suitName = cardSuit;
        this.cardRank = CardRank.valueOf(cardRank);
        this.cardSuit = CardSuit.valueOf(cardSuit);
    }

    public String getRankName() {
        return this.rankName;
    }

    public String getSuitName() {
        return this.suitName;
    }

    public int calculateCardPower(){
        return this.cardRank.getRankPower() + this.cardSuit.getSuitPowers();
    }

}
