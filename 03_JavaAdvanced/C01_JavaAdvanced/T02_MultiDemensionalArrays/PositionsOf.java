package T02_MultiDemensionalArrays.Lab;

import java.util.*;

public class PositionsOf {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[][] matrix = initializeTheMatrix(scanner);
        int numberToBeFound = Integer.parseInt(scanner.nextLine());
        List<int[]> indexes = new ArrayList<>();

        for (int rows = 0; rows < matrix.length; rows++) {
            for (int columns = 0; columns < matrix[rows].length; columns++) {
                if (matrix[rows][columns] == numberToBeFound){
                    indexes.add(new int[]{rows, columns});
                }
            }
        }
        if (indexes.isEmpty()){
            System.out.println("not found");
        } else {
            for (int[] index : indexes) {
                int row = index[0];
                int column = index[1];
                System.out.println(row + " " + column);
            }
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
