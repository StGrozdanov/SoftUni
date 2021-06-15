package MidExamPreparation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class P05_Crossfire {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<List<Integer>> matrix = initializeTheMatrix(scanner);

        String input = scanner.nextLine();

        while (!"Nuke it from orbit".equals(input)) {
            String[] tokens = input.split("\\s+");
            int row = Integer.parseInt(tokens[0]);
            int col = Integer.parseInt(tokens[1]);
            int radius = Integer.parseInt(tokens[2]);

            explodeTheMatrix(matrix, row, col, radius);

            input = scanner.nextLine();
        }
        print(matrix);
    }

    private static void explodeTheMatrix(List<List<Integer>> matrix, int row, int col, int radius) {
        for (int i = row - radius; i <= row + radius; i++) {
            if (isInRange(i, col, matrix) && i != row) {
                matrix.get(i).remove(col);
            }
        }

        for (int i = col + radius; i >= col - radius; i--) {
            if (isInRange(row, i, matrix)) {
                matrix.get(row).remove(i);
            }
        }
        matrix.removeIf(List::isEmpty);
    }

    private static boolean isInRange(int row, int col, List<List<Integer>> matrix) {
        return row >= 0 && row < matrix.size() && col >= 0 && col < matrix.get(row).size();
    }

    private static List<List<Integer>> initializeTheMatrix(Scanner scanner) {
        int[] tokens = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt)
                .toArray();
        int rows = tokens[0];
        int cols = tokens[1];

        List<List<Integer>> matrix = new ArrayList<>();

        int i = 1;
        for (int row = 0; row < rows; row++) {
            matrix.add(new ArrayList<>());
            for (int col = 0; col < cols; col++) {
                matrix.get(row).add(i);
                i++;
            }
        }
        return matrix;
    }

    private static void print(List<List<Integer>> matrix) {
        for (int rows = 0; rows < matrix.size(); rows++) {
            for (int cols = 0; cols < matrix.get(rows).size(); cols++) {
                System.out.print(matrix.get(rows).get(cols) + " ");
            }
            System.out.println();
        }
    }
}
