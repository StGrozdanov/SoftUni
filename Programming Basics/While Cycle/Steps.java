package ProgrammingBasics.WhileCycle.Exercises;

import java.util.Scanner;

public class Steps {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int stepsSum = 0;
        int diff = 0;

        while (stepsSum < 10000){
            String steps = scanner.nextLine();

            if(!steps.equals("Going home")){
                int step = Integer.parseInt(steps);
                stepsSum += step;
                diff = 10000 - stepsSum;
            }else {
                int homeStep = Integer.parseInt(scanner.nextLine());
                stepsSum += homeStep;
                diff = 10000 - stepsSum;
                if (stepsSum < 10000){
                    System.out.printf("%d more steps to reach goal.", Math.abs(diff));
                    break;
                }
            } if (stepsSum >= 10000){
                System.out.printf("Goal reached! Good job!%n%d steps over the goal!", Math.abs(diff));
            }
        }
    }
}
