package BasicsExamTest2;

import java.util.Scanner;

public class exam3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int competitors = Integer.parseInt(scanner.nextLine());
        double points = Double.parseDouble(scanner.nextLine());
        String season = scanner.nextLine();
        String place = scanner.nextLine();

        double moneyGained = 0;
        double moneyConsumed = 0;

        if ("Bulgaria".equals(place)){
            moneyGained = competitors * points;
        } else {
            moneyGained = (competitors * points) * 1.5;
        }

        switch (season){
            case "summer":
                if (place.equals("Bulgaria")){
                    moneyConsumed = 0.95 * moneyGained;
                } else {
                    moneyConsumed = 0.9 * moneyGained;
                }
                break;
            case "winter":
                if (place.equals("Bulgaria")){
                    moneyConsumed = 0.92 * moneyGained;
                } else {
                    moneyConsumed = 0.85 * moneyGained;
                }
                break;
        }

        double charityMoney = moneyConsumed * 0.75;
        double moneyLeft = moneyConsumed - charityMoney;
        double moneyPerDancer = moneyLeft / competitors;

        System.out.printf("Charity - %.2f%n", charityMoney);
        System.out.printf("Money per dancer - %.2f%n", moneyPerDancer);

    }
}
