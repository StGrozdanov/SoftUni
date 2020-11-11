package BasicsExamTest1;

import java.util.Scanner;

public class exam2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int processorCount = Integer.parseInt(scanner.nextLine());
        int people = Integer.parseInt(scanner.nextLine());
        int days = Integer.parseInt(scanner.nextLine());

        int workingHours = people * days * 8;
        double processorsInInterval = Math.floor(1.0 * workingHours / 3);

        if (processorsInInterval < processorCount){
            double losses = (processorCount - processorsInInterval) * 110.10;
            System.out.printf("Losses: -> %.2f BGN", losses);
        } else {
            double profit = (processorsInInterval - processorCount) * 110.10;
            System.out.printf("Profit: -> %.2f BGN", profit);
        }
    }
}
