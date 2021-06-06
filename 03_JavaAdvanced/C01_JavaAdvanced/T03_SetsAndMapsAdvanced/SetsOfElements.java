package T03_SetsAndMapsAdvanced.Exercise;

import java.util.*;

public class SetsOfElements {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] tokens = scanner.nextLine().split("\\s+");
        int firstLength = Integer.parseInt(tokens[0]);
        int secondLength = Integer.parseInt(tokens[1]);

        Set<Integer> first = new LinkedHashSet<>();
        Set<Integer> second = new LinkedHashSet<>();

        first = fillUp(scanner, firstLength, first);
        second = fillUp(scanner, secondLength, second);

        Set<Integer> finalSecond = second;
        first.forEach(e -> {
            if (finalSecond.contains(e)){
                System.out.print(e + " ");
            }
        });
    }

    private static Set<Integer> fillUp(Scanner scanner, int firstLength, Set<Integer> first) {
        for (int i = 0; i < firstLength; i++) {
            int num = Integer.parseInt(scanner.nextLine());
            first.add(num);
        }
        return first;
    }
}
