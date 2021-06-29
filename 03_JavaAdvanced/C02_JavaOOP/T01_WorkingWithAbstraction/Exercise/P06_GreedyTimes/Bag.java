package T01_WorkingWithAbstraction.Exercise.P06_GreedyTimes;

import java.util.LinkedHashMap;
import java.util.Map;

public class Bag {
    private long capacity;
    private Cash cash;
    private Gem gem;
    private Gold gold;
    private Map<Object, Long> itemCollection;

    public Bag(long capacity) {
        this.capacity = capacity;
        this.gold = new Gold();
        this.cash = new Cash();
        this.gem = new Gem();
    }

    public void addGold(long value) {
        if (this.capacity >= value + gold.amount()) {
            this.gold.add(value);
        }
    }

    public void addCash(String item, long value) {
        if (cash.amount() + value <= gem.amount() && this.capacity >= cash.amount() + value) {
            this.cash.add(item, value);
        }
    }

    public void addGem(String item, long value) {
        if (gem.amount() + value <= gold.amount() && this.capacity >= gem.amount() + value) {
            this.gem.add(item, value);
        }
    }

    public void emptyTheLoot() {
        itemCollection = new LinkedHashMap<>();
        if (!cash.isEmpty()) {
            itemCollection.put(cash, cash.amount());
        }
        if (!gem.isEmpty()) {
            itemCollection.put(gem, gem.amount());
        }
        if (!gold.isEmpty()) {
            itemCollection.put(gold, gold.amount());
        }
        itemCollection.entrySet().stream().sorted((o1, o2) -> {
            int result = o2.getValue().compareTo(o1.getValue());
            if (result == 0){
                return -1;
            }
            return result;})
                .forEach(object -> {
                    System.out.printf("<%s> $%s%n", object.getKey().getClass().getSimpleName(), object.getValue());
                    String objectName = object.getKey().getClass().getSimpleName();
                    switch (objectName) {
                        case "Cash":
                            cash.getCashCollection().entrySet().stream()
                                    .sorted((e1, e2) -> e2.getKey().compareTo(e1.getKey()))
                                    .forEach(i -> System.out.println("##" + i.getKey() + " - " + i.getValue()));
                            break;
                        case "Gem":
                            gem.getGemCollection().entrySet().stream()
                                    .sorted((e1, e2) -> e2.getKey().compareTo(e1.getKey()))
                                    .forEach(i -> System.out.println("##" + i.getKey() + " - " + i.getValue()));
                            break;
                        case "Gold":
                            System.out.printf("##Gold - %s%n", gold.amount());
                            break;
                    }
                });
    }
}
