package T02_MultiDemensionalArrays.Exercise;

import java.util.Arrays;
import java.util.Scanner;

public class MatrixShuffling {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[][] matrix = initializeTheMatrix(scanner);

        String input = scanner.nextLine();

        while (!"END".equals(input)) {
            String[] tokens = input.split("\\s+");
            String cmd = tokens[0];
            if (cmd.equals("swap") && tokens.length == 5) {

                int rowToExchange = Integer.parseInt(tokens[1]);
                int colToExchange = Integer.parseInt(tokens[2]);
                int row = Integer.parseInt(tokens[3]);
                int col = Integer.parseInt(tokens[4]);

                if (rowToExchange <= matrix.length - 1 && colToExchange <= matrix[matrix.length - 1].length
                        && row <= matrix.length - 1 && col <= matrix[matrix.length - 1].length) {

                    String exchanger = matrix[rowToExchange][colToExchange];

                    matrix[rowToExchange][colToExchange] = matrix[row][col];
                    matrix[row][col] = exchanger;

                    for (int rows = 0; rows < matrix.length; rows++) {
                        for (int cols = 0; cols < matrix[row].length; cols++) {
                            System.out.print(matrix[rows][cols] + " ");
                        }
                        System.out.println();
                    }

                } else {
                    System.out.println("Invalid input!");
                }

            }
            else {
                System.out.println("Invalid input!");
            }

            input = scanner.nextLine();
        }

    }

    private static String[][] initializeTheMatrix(Scanner scanner) {
        int[] rowsAndCols = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        int rows = rowsAndCols[0];
        int cols = rowsAndCols[1];

        String[][] matrix = new String[rows][cols];
        for (int i = 0; i < rows; i++) {
            matrix[i] = scanner.nextLine().split("\\s+");
        }
        return matrix;
    }
}
