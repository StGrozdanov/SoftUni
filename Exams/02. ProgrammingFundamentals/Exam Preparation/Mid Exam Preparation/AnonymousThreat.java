package ExamPreparation;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AnonymousThreat {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> items = Arrays.stream(scanner.nextLine().split("\\s+")).
                collect(Collectors.toList());

        String cmd = scanner.nextLine();

        while (!"3:1".equals(cmd)) {
            String[] tokens = cmd.split("\\s+");

            if ("merge".equals(tokens[0])) {
                int startIndex = Integer.parseInt(tokens[1]);
                int endIndex = Integer.parseInt(tokens[2]);
                boolean startIndexCondition = false;
                boolean endIndexCondition = false;

                if (startIndex <= items.size() - 1 && startIndex >= 0) {
                    startIndexCondition = true;
                }
                if (endIndex <= items.size() - 1 && endIndex >= 0) {
                    endIndexCondition = true;
                }
                if (startIndexCondition && endIndexCondition) {
                    int counter = 0;
                    for (int i = startIndex; i <= endIndex; i++) {
                        items.set(startIndex, items.get(i) + items.get(i + 1));
                        items.remove(items.get(i + 1));
                        counter++;
                        i = -1;
                        if (counter >= items.size()) {
                            break;
                        }
                    }
                } else if (startIndexCondition) {
                    int counter = 0;
                    for (int i = startIndex; i <= items.size() - 1; i++) {
                        items.set(startIndex, items.get(i) + items.get(i + 1));
                        items.remove(items.get(i + 1));
                        counter++;
                        i = -1;
                        if (counter >= items.size()) {
                            break;
                        }
                    }
                } else if (endIndexCondition) {
                    int counter = 0;
                    for (int i = endIndex; i <= items.size()-1; i++) {
                        items.set(endIndex, items.get(i) + items.get(i + 1));
                        items.remove(items.get(i + 1));
                        counter++;
                        i = -1;
                        if (counter >= items.size()) {
                            break;
                        }
                    }
                }

            } else if ("divide".equals(tokens[0])) {
                // TODO
            }
            cmd = scanner.nextLine();
        }
        System.out.println(items);
    }
}
