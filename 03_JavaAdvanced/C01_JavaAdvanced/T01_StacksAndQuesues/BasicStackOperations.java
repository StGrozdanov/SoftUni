package T01_StacksAndQuesues.Exercise;

import java.util.*;

public class BasicStackOperations {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] tokens = scanner.nextLine().split("\\s+");
        int N = Integer.parseInt(tokens[0]);
        int S = Integer.parseInt(tokens[1]);
        int X = Integer.parseInt(tokens[2]);

        ArrayDeque<Integer> numbers = new ArrayDeque<>();
        int[] num = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt)
                .toArray();
        for (int i = 0; i < N; i++) {
            numbers.push(num[i]);
        }
        if (numbers.size() >= S) {
            for (int i = 0; i < S; i++) {
                numbers.pop();
            }
        }
        if (numbers.isEmpty()) {
            System.out.println("0");
        } else if (numbers.contains(X)) {
            System.out.println("true");
        } else {
            int min = Collections.min(numbers);
            System.out.println(min);
        }
    }
}
