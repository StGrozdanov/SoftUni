package T01_StacksAndQuesues.Lab;

import java.util.ArrayDeque;
import java.util.Scanner;

public class MathPotato {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] children = scanner.nextLine().split("\\s+");
        int factor = Integer.parseInt(scanner.nextLine());
        ArrayDeque<String> queue = new ArrayDeque<>();

        int counter = 1;

        for (String child : children) {
            queue.offer(child);
        }
        while (queue.size() > 1) {
            for (int i = 1; i < factor; i++) {
                String child = queue.poll();
                if (child != null) {
                    queue.offer(child);
                }
            }
            String name = queue.peek();
            if (!prime(counter)) {
                System.out.println("Removed " + name);
                queue.poll();
            } else {
                System.out.println("Prime " + name);
            }
            counter++;
        }
        String name = queue.peek();
        System.out.println("Last is " + name);
    }
    private static boolean prime(int counter) {
        if (counter == 1) return false;
        for (int i = 2; i < counter; i++) {
            if (counter % i == 0) {
                return false;
            }
        }
        return true;
    }
}
