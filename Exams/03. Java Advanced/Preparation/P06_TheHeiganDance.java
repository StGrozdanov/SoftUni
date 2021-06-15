package MidExamPreparation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class P06_TheHeiganDance {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int playerCoordinate1 = 7;
        int playerCoordinate2 = 7;
        String playerPosition = "" + 7 + ", " + 7;
        double playerHp = 18500;
        double playerDamage = Double.parseDouble(scanner.nextLine());

        double bossHp = 3000000;
        double plagueCloudSpell = 3500;
        double eruptionSpell = 6000;

        boolean afflictionDmg = false;
        String lastSpell = "";

        while (playerHp > 0 && bossHp > 0) {
            String[] tokens = scanner.nextLine().split("\\s+");
            String spell = tokens[0];
            int row = Integer.parseInt(tokens[1]);
            int col = Integer.parseInt(tokens[2]);

            bossHp -= playerDamage;

            if (bossHp < 0) {
                if (afflictionDmg) {
                    playerHp -= plagueCloudSpell;
                    afflictionDmg = false;
                }
                continue;
            }
            if (afflictionDmg) {
                playerHp -= plagueCloudSpell;
                afflictionDmg = false;
                if (playerHp < 0) {
                    lastSpell = "Plague Cloud";
                    break;
                }
            }
            if (damagedArea(row, col, playerPosition)) {
                List<String> movement = playerMove(playerCoordinate1, playerCoordinate2);
                boolean takesNoDamage = false;
                for (String coordinate : movement) {
                    if (!damagedArea(row, col, coordinate)) {
                        playerPosition = coordinate;
                        String[] parts = coordinate.split(", ");
                        playerCoordinate1 = Integer.parseInt(parts[0]);
                        playerCoordinate2 = Integer.parseInt(parts[1]);
                        takesNoDamage = true;
                        break;
                    }
                }
                if (!takesNoDamage) {
                    if (spell.equals("Cloud")) {
                        playerHp -= plagueCloudSpell;
                        afflictionDmg = true;
                        if (playerHp < 0) {
                            lastSpell = "Plague Cloud";
                        }
                    } else if (spell.equals("Eruption")) {
                        playerHp -= eruptionSpell;
                        if (playerHp < 0) {
                            lastSpell = "Eruption";
                        }
                    }
                }
            }
            if (playerHp < 0) {
                break;
            }
        }

        if (bossHp < 0) {
            System.out.println("Heigan: Defeated!");
        } else {
            System.out.printf("Heigan: %.2f%n", bossHp);
        }
        if (playerHp < 0) {
            System.out.printf("Player: Killed by %s%n", lastSpell);
        } else {
            System.out.printf("Player: %.0f%n", playerHp);
        }
        System.out.printf("Final position: %s", playerPosition);
    }

    private static boolean damagedArea(int row, int col, String playerPosition) {
        List<String> damagedCoordinates = new ArrayList<>();

        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                String result = "" + i + ", " + j;
                damagedCoordinates.add(result);
            }
        }
        return damagedCoordinates.contains(playerPosition);
    }

    private static List<String> playerMove(int rows, int cols) {
        List<String> coordinates = new ArrayList<>();
        int movementOne = rows - 1;
        int movementTwo = cols + 1;
        int movementThree = rows + 1;
        int movementFour = cols - 1;

        if (boarders(movementOne, cols)) {
            coordinates.add(movementOne + ", " + cols);
        }
        if (boarders(rows, movementTwo)) {
            coordinates.add(rows + ", " + movementTwo);
        }
        if (boarders(movementThree, cols)) {
            coordinates.add(movementThree + ", " + cols);
        }
        if (boarders(rows, movementFour)) {
            coordinates.add(rows + ", " + movementFour);
        }
        return coordinates;
    }

    private static boolean boarders(int rows, int cols) {
        return rows >= 0 && cols >= 0 && rows <= 15 && cols <= 15;
    }
}
