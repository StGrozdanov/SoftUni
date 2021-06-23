package TestExams.Е04;

import java.util.Scanner;

public class P02_Revolt {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = Integer.parseInt(scanner.nextLine());
        int cmdCount = Integer.parseInt(scanner.nextLine());
        char[][] field = new char[size][size];
        for (int rows = 0; rows < field.length; rows++) {
            String line = scanner.nextLine();
            field[rows] = line.toCharArray();
        }
        int[] playerPosition = new int[2];
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if (field[i][j] == 'f') {
                    playerPosition[0] = i;
                    playerPosition[1] = j;
                }
            }
        }

        boolean gameOver = false;
        for (int i = 0; i < cmdCount; i++) {
            String cmd = scanner.nextLine();
            switch (cmd) {
                case "up":
                    gameOver = moveThePlayer(playerPosition, field, -1, 0);
                    break;
                case "down":
                    gameOver = moveThePlayer(playerPosition, field, 1, 0);
                    break;
                case "left":
                    gameOver = moveThePlayer(playerPosition, field, 0, -1);
                    break;
                case "right":
                    gameOver = moveThePlayer(playerPosition, field, 0, 1);
                    break;
            }
            if (gameOver) {
                break;
            }
        }
        if (gameOver) {
            System.out.println("Player won!");
        } else {
            System.out.println("Player lost!");
        }
        printTheMatrix(field);
    }

    private static boolean moveThePlayer(int[] playerPosition, char[][] field, int rowModification, int colModification) {

        int playerRow = playerPosition[0];
        int playerCol = playerPosition[1];
        int newPlayerRow = playerRow + rowModification;
        int newPlayerCol = playerCol + colModification;

        if (newPlayerRow < 0) {
            newPlayerRow = field.length - 1;
        } else if (newPlayerRow >= field.length) {
            newPlayerRow = 0;
        }
        if (newPlayerCol < 0) {
            newPlayerCol = field.length - 1;
        } else if (newPlayerCol >= field.length) {
            newPlayerCol = 0;
        }

        if (field[newPlayerRow][newPlayerCol] == '-') {
            field[playerRow][playerCol] = '-';
            field[newPlayerRow][newPlayerCol] = 'f';
            playerPosition[0] = newPlayerRow;
            playerPosition[1] = newPlayerCol;
        } else if (field[newPlayerRow][newPlayerCol] == 'T') {
            return false;
        } else if (field[newPlayerRow][newPlayerCol] == 'B') {
            field[playerRow][playerCol] = '-';
            if (isInBounds(newPlayerRow + rowModification, newPlayerCol + colModification, field)) {
                if (field[newPlayerRow + rowModification][newPlayerCol + colModification] == 'F') {
                    field[newPlayerRow + rowModification][newPlayerCol + colModification] = 'f';
                    return true;
                } else if (field[newPlayerRow + rowModification][newPlayerCol + colModification] == '-') {
                    field[newPlayerRow + rowModification][newPlayerCol + colModification] = 'f';
                    playerPosition[0] = newPlayerRow + rowModification;
                    playerPosition[1] = newPlayerCol + colModification;
                }
            } else {
                if (newPlayerRow + rowModification < 0) {
                    newPlayerRow = field.length - 1;
                } else if (newPlayerRow + rowModification >= field.length) {
                    newPlayerRow = 0;
                }
                if (newPlayerCol + colModification < 0) {
                    newPlayerCol = field.length - 1;
                } else if (newPlayerCol + colModification >= field.length) {
                    newPlayerCol = 0;
                }
                if (field[newPlayerRow][newPlayerCol] == 'F') {
                    field[newPlayerRow][newPlayerCol] = 'f';
                    return true;
                } else if (field[newPlayerRow][newPlayerCol] == '-') {
                    field[newPlayerRow][newPlayerCol] = 'f';
                    playerPosition[0] = newPlayerRow;
                    playerPosition[1] = newPlayerCol;
                }
            }
        } else if (field[newPlayerRow][newPlayerCol] == 'F') {
            field[playerRow][playerCol] = '-';
            field[newPlayerRow][newPlayerCol] = 'f';
            return true;
        }
        return false;
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