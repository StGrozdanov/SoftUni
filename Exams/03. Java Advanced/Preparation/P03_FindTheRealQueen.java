package MidExamPreparation;

import java.util.Scanner;

public class P03_FindTheRealQueen {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        char[][] chessBoard = new char[8][8];
        charMatrix(scanner, chessBoard);

        for (int rows = 0; rows < chessBoard.length; rows++) {
            for (int cols = 0; cols < chessBoard[rows].length; cols++) {
                if (chessBoard[rows][cols] == 'q' && queenIsValid(rows, cols, chessBoard))
                    System.out.print(rows + " " + cols);
            }
        }
    }

    private static boolean queenIsValid(int rows, int cols, char[][] chessBoard) {
        for (int rowsDirection = -1; rowsDirection <= 1; rowsDirection++) {
            for (int colsDirection = -1; colsDirection <= 1; colsDirection++) {

                if (rowsDirection == 0 && colsDirection == 0){
                    continue;
                }

                int currentRow = rows + rowsDirection;
                int currentCol = cols + colsDirection;

                while (validateIndexes(chessBoard, currentRow, currentCol)) {
                    if ('q' == chessBoard[currentRow][currentCol]) {
                        return false;
                    }
                    currentRow += rowsDirection;
                    currentCol += colsDirection;
                }
            }
        }
        return true;
    }

    private static boolean validateIndexes(char[][] matrix, int rows, int cols) {
        return rows >= 0 && rows < matrix.length && cols >= 0 && cols < matrix[rows].length;
    }

    private static void charMatrix(Scanner scanner, char[][] A) {
        for (int row = 0; row < 8; row++) {
            A[row] = scanner.nextLine().replaceAll("\\s+", "").toCharArray();
        }
    }
}
