package MidExamPreparation;

import java.util.ArrayDeque;
import java.util.Scanner;

public class P01_SimpleTextEditor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        StringBuilder text = new StringBuilder();
        ArrayDeque<String> MemoryStack = new ArrayDeque<>();

        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < n; i++) {

            String[] tokens = scanner.nextLine().split("\\s+");
            String cmd = tokens[0];

            switch (cmd) {
                case "1":
                    String addThisSequence = tokens[1];
                    MemoryStack.push(text.toString());
                    text.append(addThisSequence);
                    break;
                case "2":
                    MemoryStack.push(text.toString());
                    int countElementsToRemove = Integer.parseInt(tokens[1]);
                    int beginIndex = text.length() - countElementsToRemove;
                    text.delete(beginIndex, beginIndex + countElementsToRemove);
                    break;
                case "3":
                    int index = Integer.parseInt(tokens[1]);
                    System.out.println(text.charAt(index - 1));
                    break;
                case "4":
                    if (!MemoryStack.isEmpty()) {
                        String lastOperationCondition = MemoryStack.pop();
                        text = new StringBuilder(lastOperationCondition);
                    }
                    break;
            }
        }
    }
}
