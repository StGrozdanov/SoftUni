package T02_MultiDemensionalArrays.Lab;

import java.util.Arrays;
import java.util.Scanner;

public class CompareMatrices {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[][] matrix1 = initializeTheMatrix(scanner);
        int[][] matrix2 = initializeTheMatrix(scanner);
        boolean equal = false;

        if (matrix1.length == matrix2.length) {
            for (int rows = 0; rows < matrix1.length; rows++) {
                int[] firstArr = matrix1[rows];
                int[] secondArr = matrix2[rows];
                if (firstArr.length == secondArr.length) {
                    for (int columns = 0; columns < firstArr.length; columns++) {
                        if (firstArr[columns] != secondArr[columns]) {
                            equal = false;
                            break;
                        } else {
                            equal = true;
                        }
                    }
                }
                if (!equal) {
                    break;
                }
            }
        }
        if (equal) {
            System.out.println("equal");
        } else {
            System.out.println("not equal");
        }

    }

    private static int[][] initializeTheMatrix(Scanner scanner) {
        int[] rowsAndCols = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        int rows = rowsAndCols[0];
        int cols = rowsAndCols[1];

        int[][] matrix = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            matrix[i] = Arrays.stream(scanner.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }
        return matrix;
    }
}
