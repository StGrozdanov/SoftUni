package T01_WorkingWithAbstraction.Exercise.P06_GreedyTimes;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        long bagCapacity = Long.parseLong(scanner.nextLine());
        String[] items = scanner.nextLine().split("\\s+");
        Bag bag = new Bag(bagCapacity);

        for (int i = 0; i < items.length; i += 2) {
            String item = items[i];
            long itemValue = Long.parseLong(items[i + 1]);
            if (item.length() == 3) {
                //cash
                bag.addCash(item, itemValue);
            } else if (item.toLowerCase().endsWith("gem")) {
                //gem
                bag.addGem(item, itemValue);
            } else if (item.toLowerCase().equals("gold")) {
                //gold
                bag.addGold(itemValue);
            }
        }
        bag.emptyTheLoot();
    }
}