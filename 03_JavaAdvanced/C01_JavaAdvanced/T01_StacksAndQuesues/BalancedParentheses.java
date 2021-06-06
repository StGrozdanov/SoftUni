package T01_StacksAndQuesues.Exercise;

import java.util.*;

public class BalancedParentheses {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayDeque<String> stack = new ArrayDeque<>();
        String[] tokens = scanner.nextLine().split("");
        List<String> brackets = new ArrayList<>(Arrays.asList("(", "[", "{"));
        boolean valid = true;

        for (String token : tokens) {
            if (brackets.contains(token)) {
                stack.push(token);
                continue;
            }
            if (stack.size() == 0) {
                valid = false;
                break;
            }
            if (stack.peek().equals("(") && token.equals(")")) {
                stack.pop();
            } else if (stack.peek().equals("{") && token.equals("}")) {
                stack.pop();
            } else if (stack.peek().equals("[") && token.equals("]")) {
                stack.pop();
            } else {
                valid = false;
                break;
            }
        }
        if (valid) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}
