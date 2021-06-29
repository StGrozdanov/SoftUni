import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class P01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // stack
        ArrayDeque<Integer> tasks = new ArrayDeque<>();
        Arrays.stream(scanner.nextLine().split(",\\s+"))
                .map(Integer::parseInt)
                .forEach(tasks::push);

        // queue
        ArrayDeque<Integer> thread = Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayDeque::new));

        int valueToKill = Integer.parseInt(scanner.nextLine());

        while (tasks.peek() != valueToKill){
            int currentThread = thread.pop();
            if (currentThread >= tasks.peek()){
                tasks.poll();
            }
        }
        System.out.printf("Thread with value %d killed task %d%n", thread.peek(), tasks.peek());
        for (Integer integer : thread) {
            System.out.print(integer + " ");
        }
    }
}
