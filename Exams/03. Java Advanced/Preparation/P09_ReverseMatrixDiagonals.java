package MidExamPreparation;

import java.util.Arrays;
import java.util.Scanner;

public class P09_ReverseMatrixDiagonals {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[][] matrix = initializeTheMatrix(scanner);

        int rows = matrix.length - 1;
        int cols = matrix[0].length - 1;

        while (rows >= 0) {
            int i = 0;
            while (isInRange(rows - i, cols + i, matrix)) {
                System.out.print(matrix[rows - i][cols + i] + " ");
                i++;
            }
            System.out.println();
            cols--;
            if (cols < 0){
                cols = 0;
                rows--;
            }
        }
    }

    private static int[][] initializeTheMatrix(Scanner scanner) {
        int[] tokens = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt)
                .toArray();
        int rows = tokens[0];
        int cols = tokens[1];

        int[][] matrix = new int[rows][cols];

        for (int row = 0; row < matrix.length; row++) {
            matrix[row] = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt)
                    .toArray();
        }
        return matrix;
    }

    private static void print(int[][] matrix) {
        for (int rows = 0; rows < matrix.length; rows++) {
            for (int cols = 0; cols < matrix[rows].length; cols++) {
                System.out.print(matrix[rows][cols] + " ");
            }
            System.out.println();
        }
    }

    private static boolean isInRange(int row, int col, int[][] matrix) {
        return row >= 0 && row < matrix.length && col >= 0 && col < matrix[row].length;
    }
}
