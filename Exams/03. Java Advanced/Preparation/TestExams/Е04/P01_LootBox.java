package TestExams.Е04;

import java.util.*;
import java.util.stream.Collectors;

public class P01_LootBox {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayDeque<Integer> firstBox = Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayDeque::new));

        ArrayDeque<Integer> secondBox = new ArrayDeque<>();
        Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .forEach(secondBox::push);
        int claimedItems = 0;

        while (!firstBox.isEmpty() && !secondBox.isEmpty()) {
            int firstBoxValue = firstBox.peek();
            int secondBoxValue = secondBox.pop();
            int sum = firstBoxValue + secondBoxValue;
            if (sum % 2 == 0) {
                claimedItems += sum;
                firstBox.poll();
            } else {
                firstBox.offer(secondBoxValue);
            }
        }
        if (firstBox.isEmpty()) {
            System.out.println("First lootbox is empty");
        }
        if (secondBox.isEmpty()) {
            System.out.println("Second lootbox is empty");
        }
        if (claimedItems >= 100) {
            System.out.printf("Your loot was epic! Value: %d", claimedItems);
        } else {
            System.out.printf("Your loot was poor... Value: %d", claimedItems);
        }
    }
}
