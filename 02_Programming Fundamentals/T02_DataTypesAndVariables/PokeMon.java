package DataTypesAndVariables;

import java.util.Scanner;

public class PokeMon {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int pokePower = Integer.parseInt(scanner.nextLine());
        int targetDistance = Integer.parseInt(scanner.nextLine());
        int exhaustion = Integer.parseInt(scanner.nextLine());

        int pokeCounter = 0;
        int powerLeft = pokePower;

        while (targetDistance <= powerLeft){
            powerLeft -= targetDistance;
            pokeCounter++;
            if (powerLeft == pokePower * 0.5 && exhaustion != 0){
                powerLeft = powerLeft / exhaustion;
            }
        }
        System.out.println(powerLeft);
        System.out.println(pokeCounter);
    }
}
