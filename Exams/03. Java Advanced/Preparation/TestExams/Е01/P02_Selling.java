package TestExams.Е01;

import java.util.ArrayDeque;
import java.util.Scanner;

public class P02_Selling {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        char[][] bakery = initializeTheMatrix(scanner);
        int[] sellerPosition = new int[2];

        for (int rows = 0; rows < bakery.length; rows++) {
            for (int cols = 0; cols < bakery[rows].length; cols++) {
                if (bakery[rows][cols] == 'S') {
                    sellerPosition[0] = rows;
                    sellerPosition[1] = cols;
                }
            }
        }
        boolean gameOver = false;
        ArrayDeque<Integer> collectedMoney = new ArrayDeque<>();
        collectedMoney.push(0);

        while (!gameOver) {
            String direction = scanner.nextLine();
            switch (direction) {
                case "up":
                    gameOver = moveThePlayer(sellerPosition, bakery, -1, 0, collectedMoney);
                    break;
                case "down":
                    gameOver = moveThePlayer(sellerPosition, bakery, 1, 0, collectedMoney);
                    break;
                case "left":
                    gameOver = moveThePlayer(sellerPosition, bakery, 0, -1, collectedMoney);
                    break;
                case "right":
                    gameOver = moveThePlayer(sellerPosition, bakery, 0, 1, collectedMoney);
                    break;
            }

        }
        if (!isInBounds(sellerPosition[0], sellerPosition[1], bakery)) {
            System.out.println("Bad news, you are out of the bakery.");
        }
        if (collectedMoney.peek() >= 50) {
            System.out.println("Good news! You succeeded in collecting enough money!");
        }
        System.out.printf("Money: %d%n", collectedMoney.peek());
        printTheMatrix(bakery);
    }

    private static boolean moveThePlayer(int[] sellerPosition, char[][] bakery, int rowModification,
                                         int colModification, ArrayDeque<Integer> collectedMoney) {
        int playerRow = sellerPosition[0];
        int playerCol = sellerPosition[1];

        int newPlayerRow = playerRow + rowModification;
        int newPlayerCol = playerCol + colModification;

        if (isInBounds(newPlayerRow, newPlayerCol, bakery)) {
            char symbol = bakery[newPlayerRow][newPlayerCol];
            if (bakery[newPlayerRow][newPlayerCol] == 'O') {
                bakery[playerRow][playerCol] = '-';
                bakery[newPlayerRow][newPlayerCol] = '-';
                int[] newCoordinates = searchForPillar(bakery);
                sellerPosition[0] = newCoordinates[0];
                sellerPosition[1] = newCoordinates[1];
                return false;
            } else if (Character.isDigit(symbol)) {
                int newAmount = collectedMoney.pop() + Character.getNumericValue(symbol);
                collectedMoney.push(newAmount);
                bakery[newPlayerRow][newPlayerCol] = 'S';
                bakery[playerRow][playerCol] = '-';
                sellerPosition[0] = newPlayerRow;
                sellerPosition[1] = newPlayerCol;
                return collectedMoney.peek() >= 50;
            } else if (bakery[newPlayerRow][newPlayerCol] == '-') {
                bakery[playerRow][playerCol] = '-';
                bakery[newPlayerRow][newPlayerCol] = 'S';
                sellerPosition[0] = newPlayerRow;
                sellerPosition[1] = newPlayerCol;
                return false;
            }
        } else if (!isInBounds(newPlayerRow, newPlayerCol, bakery)) {
            bakery[playerRow][playerCol] = '-';
            sellerPosition[0] = newPlayerRow;
            sellerPosition[1] = newPlayerCol;
            return true;
        }
        return false;
    }

    private static int[] searchForPillar(char[][] field) {
        int[] coordinates = new int[2];
        for (int rows = 0; rows < field.length; rows++) {
            for (int cols = 0; cols < field[rows].length; cols++) {
                if (field[rows][cols] == 'O') {
                    coordinates[0] = rows;
                    coordinates[1] = cols;
                    field[rows][cols] = 'S';
                    return coordinates;
                }
            }
        }
        coordinates[0] = -1;
        coordinates[1] = -1;
        return coordinates;
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
