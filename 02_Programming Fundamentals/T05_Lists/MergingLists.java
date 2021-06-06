package Lists07;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MergingLists {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> list1 = Arrays.stream(scanner.nextLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
        List<Integer> list2 = Arrays.stream(scanner.nextLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());

        int minLength = Math.min(list1.size(), list2.size());

        for (int i = 0; i < minLength; i++) {
            System.out.print(list1.get(i) + " ");
            System.out.print(list2.get(i) + " ");
        }
        printWhatIsLeft(list1, minLength);
        printWhatIsLeft(list2, minLength);
    }

    private static void printWhatIsLeft(List<Integer> list, int minLength) {
        for (int i = minLength; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
    }
}
