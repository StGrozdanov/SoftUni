package T01_WorkingWithAbstraction.Exercise.P06_GreedyTimes;

import java.util.LinkedHashMap;
import java.util.Map;

public class Gem {
    private Map<String, Long> gemCollection;

    public Gem() {
        gemCollection = new LinkedHashMap<>();
    }

    public Map<String, Long> getGemCollection() {
        return gemCollection;
    }

    public void add(String item, long value) {
        this.gemCollection.putIfAbsent(item, 0L);
        long newValue = this.gemCollection.get(item) + value;
        this.gemCollection.put(item, newValue);
    }

    public long amount() {
        long sum = 0;
        for (Long value : gemCollection.values()) {
            sum += value;
        }
        return sum;
    }
    public boolean isEmpty(){
        return gemCollection.isEmpty();
    }
}
