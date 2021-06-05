package exam01;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Inventory {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> journal = Arrays.stream(scanner.nextLine().split(", ")).
                collect(Collectors.toList());

        String input = scanner.nextLine();

        while (!"Craft!".equals(input)) {
            String[] tokens = input.split(" - ");
            String command = tokens[0];
            switch (command) {
                case "Collect":
                    String itemToCollect = tokens[1];
                    if (!journal.contains(itemToCollect)){
                        journal.add(itemToCollect);
                    }
                    break;
                case "Drop":
                    String itemToDrop = tokens[1];
                    journal.remove(itemToDrop);
                    break;
                case "Combine Items":
                    String itemsToSplit = tokens[1];
                    String[] combination = itemsToSplit.split(":");
                    String oldItem = combination[0];
                    String newItem = combination[1];
                    if (journal.contains(oldItem)){
                        int oldItemIndex = journal.indexOf(oldItem);
                        journal.add(oldItemIndex+1, newItem);
                    }
                    break;
                case "Renew":
                    String itemToRenew = tokens[1];
                    if (journal.contains(itemToRenew)){
                        journal.remove(itemToRenew);
                        journal.add(itemToRenew);
                    }
                    break;
            }

            input = scanner.nextLine();
        }
        System.out.println(String.join(", ", journal));
    }
}
