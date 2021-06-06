package T02_MultiDemensionalArrays.Lab;

import java.util.Arrays;
import java.util.Scanner;

public class TwoByTwoMatrixAndItsSum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[][] matrix = initializeTheMatrix(scanner);
        int maxSum = Integer.MIN_VALUE;
        int maxColumn = 0;
        int maxRow = 0;

        for (int rows = 0; rows < matrix.length - 1; rows++) {
            for (int columns = 0; columns < matrix[rows].length - 1; columns++) {
                int currentSum = matrix[rows][columns] + matrix[rows][columns + 1]
                        + matrix[rows + 1][columns] + matrix[rows + 1][columns + 1];
                if (currentSum > maxSum){
                    maxSum = currentSum;
                    maxRow = rows;
                    maxColumn = columns;
                }
            }
        }
        System.out.println(matrix[maxRow][maxColumn] + " " + matrix[maxRow][maxColumn+1]);
        System.out.println(matrix[maxRow+1][maxColumn] + " " + matrix[maxRow+1][maxColumn+1]);
        System.out.println(maxSum);
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
        return matrix;
    }
}
