package ProgrammingBasics.AdvancedConditionalStatements.AdvConditionsExercise;

import java.util.Scanner;

public class OnTimeForExam {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int hour = Integer.parseInt(scanner.nextLine());
        int minute = Integer.parseInt(scanner.nextLine());
        int arrivalHour = Integer.parseInt(scanner.nextLine());
        int arrivalMinute = Integer.parseInt(scanner.nextLine());

        double timeInMinutes = hour * 60 + minute;
        double arrivalInMinutes = arrivalHour * 60 + arrivalMinute;
        double difference = timeInMinutes - arrivalInMinutes;

        if (timeInMinutes < arrivalInMinutes) {
            System.out.println("Late");

            if (difference > -60){
                System.out.printf("%.0f minutes after the start", Math.abs(difference));
            } else {
                double hour1 = difference / -60;
                double minute1 = difference % 60;
                if (minute1 > -10){
                    System.out.printf("%.0f:0%.0f hours after the start", Math.floor(hour1), Math.abs(minute1));
                } else {
                    System.out.printf("%.0f:%.0f hours after the start", Math.floor(hour1), Math.abs(minute1));
                }
            }

        } else if (timeInMinutes == arrivalInMinutes || difference <= 30) {
            System.out.println("On time");

            if (difference != 0){
                System.out.printf("%.0f minutes before the start", difference);
            }

        } else if (difference > 30) {
            System.out.println("Early");

            if (difference < 60){
                System.out.printf("%.0f minutes before the start", difference);
            } else {
                double hour1 = difference / 60;
                double minute1 = difference % 60;
                if (minute1 < 10){
                    System.out.printf("%.0f:0%.0f hours before the start", Math.floor(hour1), minute1);
                } else {
                    System.out.printf("%.0f:%.0f hours before the start", Math.floor(hour1), minute1);
                }
            }

        }

    }
}
