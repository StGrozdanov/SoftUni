package T01_StacksAndQuesues.Exercise;

import java.util.ArrayDeque;
import java.util.Scanner;

public class ReverseNumbersWithAStack {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] tokens = scanner.nextLine().split("\\s+");
        ArrayDeque<Integer> numbersReversed = new ArrayDeque<>();

        for (String token : tokens) {
            int number = Integer.parseInt(token);
            numbersReversed.push(number);
        }
        for (Integer integer : numbersReversed) {
            System.out.print(integer + " ");
        }
    }
}
