package T01_WorkingWithAbstraction.Exercise.P03_CardsWithPower;

public enum CardSuit {
    CLUBS(0),
    DIAMONDS(13),
    HEARTS(26),
    SPADES(39);
    private int suitPowers;

    CardSuit(int suitPowers) {
        this.suitPowers = suitPowers;
    }

    public int getSuitPowers() {
        return suitPowers;
    }
}
