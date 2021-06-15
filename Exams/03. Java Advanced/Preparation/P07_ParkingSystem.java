package MidExamPreparation;

import java.util.Arrays;
import java.util.Scanner;

public class P07_ParkingSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[][] matrix = initializeTheMatrix(scanner);
        String input = scanner.nextLine();

        while (!"stop".equals(input)) {
            int[] tokens = Arrays.stream(input.split("\\s+")).mapToInt(Integer::parseInt)
                    .toArray();
            int entranceRow = tokens[0];
            int desiredRow = tokens[1];
            int desiredCol = tokens[2];
            int distance = (Math.abs(entranceRow - desiredRow)) + 1;

            if (matrix[desiredRow][desiredCol] == 0) {
                distance += desiredCol;
                matrix[desiredRow][desiredCol] = 1;
            } else {
                int closestDistance = findTheClosestParkingSpot(matrix, desiredRow, desiredCol);
                if (closestDistance > 0) {
                    distance += closestDistance;
                } else {
                    distance = -1;
                }
            }
            if (distance > 0) {
                System.out.println(distance);
            } else {
                System.out.printf("Row %d full%n", desiredRow);
            }
            input = scanner.nextLine();
        }
    }

    private static int findTheClosestParkingSpot(int[][] matrix, int desiredRow, int desiredCol) {
        for (int i = 1; i < matrix[desiredRow].length; i++) {
            if (desiredCol - i > 0 && matrix[desiredRow][desiredCol - i] == 0) {
                matrix[desiredRow][desiredCol - i] = 1;
                return desiredCol - i;
            }
        }
        for (int i = 1; i < matrix[desiredRow].length; i++) {
            if (desiredCol + i < matrix[desiredRow].length && matrix[desiredRow][desiredCol + i] == 0) {
                matrix[desiredRow][desiredCol + i] = 1;
                return desiredCol + i;
            }
        }
        return -1;
    }

    private static int[][] initializeTheMatrix(Scanner scanner) {
        int[] rowsAndCols = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        int rows = rowsAndCols[0];
        int cols = rowsAndCols[1];

        int[][] matrix = new int[rows][cols];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 1; j < matrix[i].length; j++) {
                matrix[i][0] = 1;
                matrix[i][j] = 0;
            }
        }
        return matrix;
    }
}
