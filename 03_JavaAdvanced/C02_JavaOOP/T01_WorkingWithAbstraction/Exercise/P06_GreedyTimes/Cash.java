package T01_WorkingWithAbstraction.Exercise.P06_GreedyTimes;

import java.util.LinkedHashMap;
import java.util.Map;

public class Cash {
    private Map<String, Long> cashCollection;

    public Cash() {
        cashCollection = new LinkedHashMap<>();
    }

    public Map<String, Long> getCashCollection() {
        return cashCollection;
    }

    public void add(String item, long value) {
        this.cashCollection.putIfAbsent(item, 0L);
        long newValue = this.cashCollection.get(item) + value;
        this.cashCollection.put(item, newValue);
    }

    public long amount() {
        long sum = 0;
        for (Long value : cashCollection.values()) {
            sum += value;
        }
        return sum;
    }
    public boolean isEmpty(){
        return cashCollection.isEmpty();
    }
}
