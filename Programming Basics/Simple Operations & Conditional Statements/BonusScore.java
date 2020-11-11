package ProgrammingBasics;

import java.util.Scanner;

public class BonusScore {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int points = Integer.parseInt(scanner.nextLine());
        double bonus = 0;
        double bonusBonus = 0;

        if (points <= 100){
           bonus = 5;
        } else if (points <= 999){
            bonus = points * 0.2;
        } else {
            bonus = points * 0.1;
        }
        if (points % 2 == 0){
            bonusBonus = 1;
        } else if (points % 10 == 5) {
            bonusBonus = 2;
        }

        double totalBonus = bonus + bonusBonus;
        double totalPoints = points + totalBonus;

        System.out.println(totalBonus);
        System.out.println(totalPoints);
    }
}
