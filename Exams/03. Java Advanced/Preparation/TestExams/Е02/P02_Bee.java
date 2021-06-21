package TestExams.Е02;

import java.util.Scanner;

public class P02_Bee {
    static int flowerCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        char[][] field = initializeTheMatrix(scanner);
        int[] beePosition = new int[2];

        for (int rows = 0; rows < field.length; rows++) {
            for (int cols = 0; cols < field[rows].length; cols++) {
                if (field[rows][cols] == 'B') {
                    beePosition[0] = rows;
                    beePosition[1] = cols;
                }
            }
        }

        boolean gameOver = false;
        String input = scanner.nextLine();

        while (!gameOver && (!"End".equals(input))) {
            switch (input) {
                case "up":
                    gameOver = moveTheBee(beePosition, field, -1, 0);
                    break;
                case "down":
                    gameOver = moveTheBee(beePosition, field, 1, 0);
                    break;
                case "left":
                    gameOver = moveTheBee(beePosition, field, 0, -1);
                    break;
                case "right":
                    gameOver = moveTheBee(beePosition, field, 0, 1);
                    break;
            }
            if (!gameOver) {
                input = scanner.nextLine();
            }
        }
        if (gameOver) {
            System.out.println("The bee got lost!");
        }
        if (flowerCount < 5) {
            int flowersNeeded = 5 - flowerCount;
            System.out.printf("The bee couldn't pollinate the flowers, she needed %d flowers more%n", flowersNeeded);
        } else {
            System.out.printf("Great job, the bee manage to pollinate %d flowers!%n", flowerCount);
        }
        printTheMatrix(field);
    }

    private static boolean moveTheBee(int[] beePosition, char[][] field, int rowModification,
                                      int colModification) {
        int beeRow = beePosition[0];
        int beeCol = beePosition[1];

        int newBeeRow = beeRow + rowModification;
        int newBeeCol = beeCol + colModification;

        if (isInBounds(newBeeRow, newBeeCol, field)) {
            if (field[newBeeRow][newBeeCol] == 'O') {
                field[beeRow][beeCol] = '.';
                field[newBeeRow][newBeeCol] = '.';
                if (isInBounds(newBeeRow + rowModification, newBeeCol + colModification, field)) {
                    if (field[newBeeRow + rowModification][newBeeCol + colModification] == 'f') {
                        flowerCount++;
                        field[newBeeRow + rowModification][newBeeCol + colModification] = 'B';
                        beePosition[0] = newBeeRow + rowModification;
                        beePosition[1] = newBeeCol + colModification;
                        return false;
                    }
                    field[newBeeRow + rowModification][newBeeCol + colModification] = 'B';
                    beePosition[0] = newBeeRow + rowModification;
                    beePosition[1] = newBeeCol + colModification;
                    return false;
                }
                return true;
            } else if (field[newBeeRow][newBeeCol] == 'f') {
                flowerCount++;
                field[beeRow][beeCol] = '.';
                field[newBeeRow][newBeeCol] = 'B';
                beePosition[0] = newBeeRow;
                beePosition[1] = newBeeCol;
                return false;
            } else if (field[newBeeRow][newBeeCol] == '.') {
                field[beeRow][beeCol] = '.';
                field[newBeeRow][newBeeCol] = 'B';
                beePosition[0] = newBeeRow;
                beePosition[1] = newBeeCol;
                return false;
            }
        } else if (!isInBounds(newBeeRow, newBeeCol, field)) {
            field[beeRow][beeCol] = '.';
            return true;
        }
        return false;
    }

    private static char[][] initializeTheMatrix(Scanner scanner) {
        int size = Integer.parseInt(scanner.nextLine());
        char[][] matrix = new char[size][size];
        for (int i = 0; i < size; i++) {
            String line = scanner.nextLine();
            matrix[i] = line.toCharArray();
        }
        return matrix;
    }

    private static void printTheMatrix(char[][] matrix) {
        for (int rows = 0; rows < matrix.length; rows++) {
            for (int cols = 0; cols < matrix[rows].length; cols++) {
                System.out.print(matrix[rows][cols]);
            }
            System.out.println();
        }
    }

    private static boolean isInBounds(int rows, int cols, char[][] matrix) {
        return rows >= 0 && rows < matrix.length && cols >= 0 && cols < matrix.length;
    }
}
