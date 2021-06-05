package ExamPreparation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LadyBugs {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int fieldLength = Integer.parseInt(scanner.nextLine());
        String[] bugIndexes = scanner.nextLine().split("\\s+");
        int[] bugInIndex = new int[bugIndexes.length];
        for (int i = 0; i < bugInIndex.length; i++) {
            bugInIndex[i] = Integer.parseInt(bugIndexes[i]);
        }
        List<Integer> initialField = new ArrayList<>();
        for (int i = 0; i < fieldLength; i++) {
            initialField.add(i, 0);
        }
        for (int i = 0; i < bugInIndex.length; i++) {
            if (bugInIndex[i] <= initialField.size()-1 &&
                    bugInIndex[i] >= 0) {
                initialField.set(bugInIndex[i], 1);
            }
        }

        String cmd = scanner.nextLine();

        while(!"end".equals(cmd)){
            String[] tokens = cmd.split("\\s+");
            int targetIndex = Integer.parseInt(tokens[0]);
            String direction = tokens[1];
            int getToIndex = Integer.parseInt(tokens[2]);

            if (targetIndex >= 0 && targetIndex <= initialField.size()-1 &&
                    initialField.get(targetIndex) == 1) {
                int toGoRight = targetIndex + getToIndex;
                int toGoLeft = targetIndex - getToIndex;
                if ("right".equals(direction)) {
                    if (toGoRight <= initialField.size() - 1 &&
                            initialField.get(toGoRight) == 0) {
                        initialField.set(targetIndex, 0);
                        initialField.set(toGoRight, 1);
                    } else {
                        initialField.set(targetIndex, 0);
                    }
                } else if ("left".equals(direction)) {
                    if (toGoLeft <= initialField.size() - 1 &&
                            initialField.get(toGoLeft) == 0) {
                        initialField.set(targetIndex, 0);
                        initialField.set(toGoLeft, 1);
                    } else {
                        initialField.set(targetIndex, 0);
                    }
                }
            }
            cmd = scanner.nextLine();
        }
        for (int field:initialField) {
            System.out.print(field + " ");
        }
    }
}
