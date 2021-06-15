package MidExamPreparation;

import java.util.Arrays;
import java.util.Scanner;

public class P10_TheMatrix {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[][] matrix = initializeTheMatrix(scanner);
        String targetSymbol = scanner.nextLine();
        String[] startingCoordinates = scanner.nextLine().split("\\s+");

        int startingRow = Integer.parseInt(startingCoordinates[0]);
        int startingCol = Integer.parseInt(startingCoordinates[1]);
        String symbolToReplace = matrix[startingRow][startingCol];

        matrix[startingRow][startingCol] = targetSymbol;
        fillTheMatrix(startingRow, startingCol, targetSymbol, symbolToReplace, matrix);
        print(matrix);
    }

    private static void fillTheMatrix(int startingRow, int startingCol, String targetSymbol, String symbolToReplace,
                                      String[][] matrix) {
        if (isInRange(startingRow + 1, startingCol, matrix) && matrix[startingRow + 1][startingCol]
                .equals(symbolToReplace)) {
            matrix[startingRow + 1][startingCol] = targetSymbol;
            fillTheMatrix(startingRow + 1, startingCol, targetSymbol, symbolToReplace, matrix);
        }
        if (isInRange(startingRow - 1, startingCol, matrix) && matrix[startingRow - 1][startingCol]
                .equals(symbolToReplace)) {
            matrix[startingRow - 1][startingCol] = targetSymbol;
            fillTheMatrix(startingRow - 1, startingCol, targetSymbol, symbolToReplace, matrix);
        }
        if (isInRange(startingRow, startingCol + 1, matrix) && matrix[startingRow][startingCol + 1]
                .equals(symbolToReplace)) {
            matrix[startingRow][startingCol + 1] = targetSymbol;
            fillTheMatrix(startingRow, startingCol + 1, targetSymbol, symbolToReplace, matrix);
        }
        if (isInRange(startingRow, startingCol - 1, matrix) && matrix[startingRow][startingCol - 1]
                .equals(symbolToReplace)) {
            matrix[startingRow][startingCol - 1] = targetSymbol;
            fillTheMatrix(startingRow, startingCol - 1, targetSymbol, symbolToReplace, matrix);
        }
    }

    private static String[][] initializeTheMatrix(Scanner scanner) {
        int[] tokens = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt)
                .toArray();
        int rows = tokens[0];
        int cols = tokens[1];

        String[][] matrix = new String[rows][cols];

        for (int row = 0; row < matrix.length; row++) {
            matrix[row] = scanner.nextLine().split("\\s+");
        }
        return matrix;
    }

    private static void print(String[][] matrix) {
        for (int rows = 0; rows < matrix.length; rows++) {
            for (int cols = 0; cols < matrix[rows].length; cols++) {
                System.out.print(matrix[rows][cols]);
            }
            System.out.println();
        }
    }

    private static boolean isInRange(int row, int col, String[][] matrix) {
        return row >= 0 && row < matrix.length && col >= 0 && col < matrix[row].length;
    }
}
