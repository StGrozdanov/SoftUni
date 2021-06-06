package T02_MultiDemensionalArrays.Exercise;

import java.util.Scanner;

public class MatrixOfPalindromes2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] tokens = scanner.nextLine().split("\\s+");
        int rows = Integer.parseInt(tokens[0]);
        int columns = Integer.parseInt(tokens[1]);
        String[][] matrix = new String[rows][columns];

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                char firstAndLast = (char) ((row) + 97);
                char mid = (char) ((row + col) + 97);
                StringBuilder text = new StringBuilder();
                text.append(firstAndLast);
                text.append(mid);
                text.append(firstAndLast);
                matrix[row][col] = String.valueOf(text);
                System.out.print(matrix[row][col] + " ");
            }
            System.out.println();
        }

    }
}
