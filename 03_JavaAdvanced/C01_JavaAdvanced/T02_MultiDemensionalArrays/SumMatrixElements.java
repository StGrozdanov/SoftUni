package T02_MultiDemensionalArrays.Lab;

import java.util.Arrays;
import java.util.Scanner;

public class SumMatrixElements {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[][] matrix = initializeTheMatrix(scanner);

        int sum = Arrays.stream(matrix).mapToInt(arr -> Arrays.stream(arr).sum()).sum();

        System.out.println(sum);
    }

    private static int[][] initializeTheMatrix(Scanner scanner) {
        int[] rowsAndCols = Arrays.stream(scanner.nextLine().split(",\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        int rows = rowsAndCols[0];
        int cols = rowsAndCols[1];

        int[][] matrix = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            matrix[i] = Arrays.stream(scanner.nextLine().split(",\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }
        System.out.println(rows);
        System.out.println(cols);
        return matrix;
    }
}
