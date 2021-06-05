package exam01;

import java.util.Scanner;

public class MuOnline {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] actions = scanner.nextLine().split("\\|");

        int initialHp = 100;
        int initialBitCoins = 0;
        int counter = 0;

        for (int i = 0; i < actions.length; i++) {
            counter++;
            String[] command = actions[i].split(" ");
            if ("potion".equals(command[0])){
                int heal = Integer.parseInt(command[1]);
                int hpAfterHeal = heal + initialHp;
                if (hpAfterHeal > 100){
                    hpAfterHeal = 100;
                }
                int healAmount = hpAfterHeal - initialHp;
                initialHp = hpAfterHeal;
                System.out.printf("You healed for %d hp.%n", healAmount);
                System.out.printf("Current health: %d hp.%n", hpAfterHeal);
            } else if ("chest".equals(command[0])){
                int bitcoinsFound = Integer.parseInt(command[1]);
                initialBitCoins += bitcoinsFound;
                System.out.printf("You found %d bitcoins.%n", bitcoinsFound);

            } else {
                int damage = Integer.parseInt(command[1]);
                initialHp -= damage;
                if (initialHp > 0){
                    System.out.printf("You slayed %s.%n", command[0]);
                } else {
                    System.out.printf("You died! Killed by %s.%n", command[0]);
                    System.out.printf("Best room: %d", counter);
                    return;
                }
            }
        }
        if (initialHp != 0){
            System.out.println("You've made it!");
            System.out.printf("Bitcoins: %d%n", initialBitCoins);
            System.out.printf("Health: %d%n", initialHp);
        }

    }
}
