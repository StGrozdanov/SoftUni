package ProgrammingBasics.WhileCycle.Exercises;

import java.util.Scanner;

public class Coins {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double input = Double.parseDouble(scanner.nextLine());
        double cents = Math.round(input * 100);
        int centCounter = 0;

        while (cents > 0){
            if (cents >= 200){
                cents -= 200;
                centCounter++;
            } else if (cents >= 100){
                cents -= 100;
                centCounter++;
            }else if (cents >= 50){
                cents -= 50;
                centCounter++;
            }else if (cents >= 20){
                cents -= 20;
                centCounter++;
            }else if (cents >= 10){
                cents -= 10;
                centCounter++;
            }else if (cents >= 5){
                cents -= 5;
                centCounter++;
            }else if (cents >= 2){
                cents -= 2;
                centCounter++;
            }else if (cents >= 1){
                cents -= 1;
                centCounter++;
            }
        }
        System.out.println(centCounter);
    }
}
