package midExam;

import java.util.Scanner;

public class problem1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int biscuitsPerWorker = Integer.parseInt(scanner.nextLine());
        int countOfWorkers = Integer.parseInt(scanner.nextLine());
        int otherFactoryBiscuits = Integer.parseInt(scanner.nextLine());

        double biscuitsPerMonth = 0;

        for (int i = 1; i <= 30 ; i++) {
            if (i % 3 == 0){
                biscuitsPerMonth += Math.floor((biscuitsPerWorker * countOfWorkers) * 0.75);
            } else {
                biscuitsPerMonth += biscuitsPerWorker * countOfWorkers;
            }
        }

        System.out.printf("You have produced %.0f biscuits for the past month.%n", biscuitsPerMonth);

        double productionDifference = biscuitsPerMonth - otherFactoryBiscuits;

        if (productionDifference > 0){
            double percentage = (productionDifference / otherFactoryBiscuits) * 100;
            System.out.printf("You produce %.2f percent more biscuits.%n", percentage);
        }else {
            double percentage = Math.abs((productionDifference / otherFactoryBiscuits) * 100);
            System.out.printf("You produce %.2f percent less biscuits.%n", percentage);
        }

    }
}
