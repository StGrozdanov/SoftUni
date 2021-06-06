package T01_StacksAndQuesues.Lab;

import java.util.ArrayDeque;
import java.util.Scanner;

public class BrowserHistoryUpdate {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        ArrayDeque<String> pages = new ArrayDeque<>();
        ArrayDeque<String> queue = new ArrayDeque<>();

        while (!"Home".equals(input)) {
            if (!input.equals("back") && !input.equals("forward")) {
                System.out.println(input);
                pages.push(input);
                queue.clear();
            } else {
                switch (input) {
                    case "back":
                        if (pages.size() > 1) {
                            queue.offer(pages.pop());
                            System.out.println(pages.peek());
                        } else {
                            System.out.println("no previous URLs");
                        }
                        break;
                    case "forward":
                        if (queue.size() > 0) {
                            System.out.println(queue.peek());
                            pages.push(queue.poll());
                        } else {
                            System.out.println("no next URLs");
                        }
                        break;
                }
            }
            input = scanner.nextLine();
        }
    }
}
