package T01_WorkingWithAbstraction.Exercise.P06_GreedyTimes;

public class Gold {
    private long goldAmount;

    public Gold() {
        this.goldAmount = -1;
    }

    public void add(long value) {
        if (goldAmount == -1) {
            goldAmount = 0;
        }
        this.goldAmount += value;
    }

    public long amount() {
        return this.goldAmount;
    }

    public boolean isEmpty(){
        return goldAmount == -1;
    }
}
