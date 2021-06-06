package T01_StacksAndQuesues.Exercise;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Scanner;

public class MaximumElement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = Integer.parseInt(scanner.nextLine());
        ArrayDeque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            String[] tokens = scanner.nextLine().split("\\s+");
            String cmd = tokens[0];
            switch (cmd) {
                case "1":
                    int num = Integer.parseInt(tokens[1]);
                    stack.push(num);
                    break;
                case "2":
                    if (stack.size() >= 1){
                        stack.pop();
                    }
                    break;
                case "3":
                    System.out.println(Collections.max(stack));
                    break;
            }
        }
    }
}
