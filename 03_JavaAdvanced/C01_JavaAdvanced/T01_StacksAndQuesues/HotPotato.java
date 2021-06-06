package T01_StacksAndQuesues.Lab;

import java.util.ArrayDeque;
import java.util.Scanner;

public class HotPotato {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] children = scanner.nextLine().split("\\s+");
        int factor = Integer.parseInt(scanner.nextLine());
        ArrayDeque<String> queue = new ArrayDeque<>();

        for (String child : children) {
            queue.offer(child);
        }
        while (queue.size() > 1){
            for (int i = 1; i < factor; i++) {
                String child = queue.poll();
                if (child != null) {
                    queue.offer(child);
                }
            }
            String name = queue.poll();
            System.out.println("Removed " + name);
        }
        String name = queue.peek();
        System.out.println("Last is " + name);
    }
}
