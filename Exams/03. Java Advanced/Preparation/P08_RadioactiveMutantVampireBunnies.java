package MidExamPreparation;

import java.util.Arrays;
import java.util.Scanner;

public class P08_RadioactiveMutantVampireBunnies {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[][] field = initializeTheMatrix(scanner);
        String[] commands = scanner.nextLine().split("");
        String coordinates = findTheCoordOfThePlayer(field);

        boolean gameOver = false;
        String outcome = "";

        for (String command : commands) {
            String[] tokens = coordinates.split(" ");
            int playerRow = Integer.parseInt(tokens[0]);
            int playerCol = Integer.parseInt(tokens[1]);

            coordinates = moveThePlayer(command, playerRow, playerCol, field);
            tokens = coordinates.split(" ");

            switch (tokens[0]) {
                case "won:":
                case "dead:":
                    gameOver = true;
                    outcome = coordinates;
                    break;
                default:
                    playerRow = Integer.parseInt(tokens[0]);
                    playerCol = Integer.parseInt(tokens[1]);
            }
            moveTheBunny(field);
            if (gameOver) {
                break;
            } else if (findTheCoordOfThePlayer(field).equals("dead:")) {
                outcome = "dead:" + " " + playerRow + " " + playerCol;
                break;
            }
        }
        print(field);
        System.out.println(outcome);
    }

    private static void moveTheBunny(String[][] field) {
        for (int rows = 0; rows < field.length; rows++) {
            for (int cols = 0; cols < field[rows].length; cols++) {
                if (field[rows][cols].equals("B")) {
                    if (isInRange(rows + 1, cols, field)) {
                        field[rows + 1][cols] = "MOVE";
                    }
                    if (isInRange(rows - 1, cols, field)) {
                        field[rows - 1][cols] = "MOVE";
                    }
                    if (isInRange(rows, cols + 1, field)) {
                        field[rows][cols + 1] = "MOVE";
                    }
                    if (isInRange(rows, cols - 1, field)) {
                        field[rows][cols - 1] = "MOVE";
                    }
                }
            }
        }
        for (int rows = 0; rows < field.length; rows++) {
            for (int cols = 0; cols < field[rows].length; cols++) {
                if (field[rows][cols].equals("MOVE")) {
                    field[rows][cols] = "B";
                }
            }
        }
    }

    private static String moveThePlayer(String command, int playerRow, int playerCol, String[][] field) {
        field[playerRow][playerCol] = ".";
        switch (command) {
            case "L":
                playerCol--;
                if (isInRange(playerRow, playerCol, field)) {
                    if (field[playerRow][playerCol].equals("B")) {
                        return "dead:" + " " + playerRow + " " + playerCol;
                    } else {
                        field[playerRow][playerCol] = "P";
                    }
                } else {
                    playerCol++;
                    return "won:" + " " + playerRow + " " + playerCol;
                }
                break;
            case "R":
                playerCol++;
                if (isInRange(playerRow, playerCol, field)) {
                    if (field[playerRow][playerCol].equals("B")) {
                        return "dead:" + " " + playerRow + " " + playerCol;
                    } else {
                        field[playerRow][playerCol] = "P";
                    }
                } else {
                    playerCol--;
                    return "won:" + " " + playerRow + " " + playerCol;
                }
                break;
            case "U":
                playerRow--;
                if (isInRange(playerRow, playerCol, field)) {
                    if (field[playerRow][playerCol].equals("B")) {
                        return "dead:" + " " + playerRow + " " + playerCol;
                    } else {
                        field[playerRow][playerCol] = "P";
                    }
                } else {
                    playerRow++;
                    return "won:" + " " + playerRow + " " + playerCol;
                }
                break;
            case "D":
                playerRow++;
                if (isInRange(playerRow, playerCol, field)) {
                    if (field[playerRow][playerCol].equals("B")) {
                        return "dead:" + " " + playerRow + " " + playerCol;
                    } else {
                        field[playerRow][playerCol] = "P";
                    }
                } else {
                    playerRow--;
                    return "won:" + " " + playerRow + " " + playerCol;
                }
                break;
        }
        return playerRow + " " + playerCol;
    }

    private static String findTheCoordOfThePlayer(String[][] field) {
        for (int rows = 0; rows < field.length; rows++) {
            for (int cols = 0; cols < field[rows].length; cols++) {
                if (field[rows][cols].equals("P")) {
                    return rows + " " + cols;
                }
            }
        }
        return "dead:";
    }

    private static String[][] initializeTheMatrix(Scanner scanner) {
        int[] tokens = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt)
                .toArray();
        int rows = tokens[0];
        int cols = tokens[1];

        String[][] matrix = new String[rows][cols];

        for (int row = 0; row < matrix.length; row++) {
            matrix[row] = scanner.nextLine().split("");
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
