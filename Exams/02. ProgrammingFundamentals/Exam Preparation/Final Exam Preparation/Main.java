package exam01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> chest = Arrays.stream(scanner.nextLine().split("\\|")).collect(Collectors.toList());

        String input = scanner.nextLine();

        while (!"Yohoho!".equals(input)) {
            String[] tokens = input.split("\\s+");
            String cmd = tokens[0];
            switch (cmd) {
                case "Loot":
                    for (int i = 1; i < tokens.length; i++) {
                        if (!chest.contains(tokens[i])) {
                            chest.add(0, tokens[i]);
                        }
                    }
                    break;
                case "Drop":
                    int index = Integer.parseInt(tokens[1]);
                    if (index >= 0 && index < chest.size()) {
                        String itemToAdd = chest.get(index);
                        chest.remove(itemToAdd);
                        chest.add(itemToAdd);
                    }
                    break;
                case "Steal":
                    int count = Integer.parseInt(tokens[1]);
                    List<String> removedItems = new ArrayList<>();
                    String output = "";
                    if (count >= chest.size()) {
                        output = String.join(", ", chest);
                        System.out.println(output);
                        chest.clear();
                    } else {
                        for (int i = 0; i < count; i++) {
                            int lastIndex = chest.size() - 1;
                            removedItems.add(0, chest.remove(lastIndex));
                        }
                        output = String.join(", ", removedItems);
                        System.out.println(output);
                    }
                    break;
            }
            input = scanner.nextLine();
        }
        if (chest.size() > 0) {
            int itemsLength = 0;
            for (String s : chest) {
                itemsLength += s.length();
            }
            double result = 1.0 * itemsLength / chest.size();
            System.out.printf("Average treasure gain: %.2f pirate credits.", result);
        } else {
            System.out.println("Failed treasure hunt.");
        }
    }
}