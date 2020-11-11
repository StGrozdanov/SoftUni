package BasicsExamTest2;

import java.util.Scanner;

public class exam4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = Integer.parseInt(scanner.nextLine());
        double M = Double.parseDouble(scanner.nextLine());

        double firstDistance = M;
        double distancePerDay = 0;
        double distanceNextDay = 0;

        for (int i = 0; i < N; i++){
            int percentage = Integer.parseInt(scanner.nextLine());
            distancePerDay = M * ((1.0 * percentage / 100)+1);
            M = distancePerDay;
            distanceNextDay += distancePerDay;
        }

        double totalDistance = firstDistance + distanceNextDay;

        if (totalDistance < 1000){
            double missedDistance = 1000 - totalDistance;
            System.out.printf("Sorry Mrs. Ivanova, you need to run %.0f more kilometers", Math.ceil(missedDistance));

        } else {
            double kmMore = totalDistance - 1000;
            System.out.printf("You've done a great job running %.0f more kilometers!", Math.ceil(kmMore));
        }

    }
}
