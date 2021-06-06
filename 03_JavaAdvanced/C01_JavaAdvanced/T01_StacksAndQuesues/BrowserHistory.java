package T01_StacksAndQuesues.Lab;

import java.util.ArrayDeque;
import java.util.Scanner;

public class BrowserHistory {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        ArrayDeque<String> pages = new ArrayDeque<>();
        int counter = 0;

        while (!"Home".equals(input)){
            if (!input.equals("back")){
                System.out.println(input);
                counter++;
                pages.push(input);
            } else if (counter <= 1) {
                System.out.println("no previous URLs");
            } else {
                counter--;
                pages.pop();
                System.out.println(pages.peek());
            }
            input = scanner.nextLine();
        }
    }
}
