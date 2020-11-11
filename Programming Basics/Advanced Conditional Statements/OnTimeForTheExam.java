package ProgrammingBasics.AdvancedConditionalStatements.AdvConditionsExercise;

import java.util.Scanner;

public class OnTimeForTheExam {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int hour = Integer.parseInt(scanner.nextLine());
        int minute = Integer.parseInt(scanner.nextLine());
        int arrivalHour = Integer.parseInt(scanner.nextLine());
        int arrivalMinute = Integer.parseInt(scanner.nextLine());

        double timeInMinutes = hour * 60 + minute;
        double arrivalInMinutes = arrivalHour * 60 + arrivalMinute;
        double arrivalInMinutesPlus30 = timeInMinutes - arrivalInMinutes;

        if (timeInMinutes < arrivalInMinutes) {
            System.out.println("Late");
        } else if (timeInMinutes == arrivalInMinutes || arrivalInMinutesPlus30 <= 30) {
            System.out.println("On time");
        } else if (arrivalInMinutesPlus30 > 30) {
            System.out.println("Early");
        }
        if (arrivalInMinutesPlus30 >= 0) {
            if (arrivalInMinutesPlus30 <= 59) {
                System.out.printf("%.0f minutes before start", arrivalInMinutesPlus30);
            } else if (arrivalInMinutesPlus30 >= 60) {
                double hour1 = arrivalInMinutesPlus30 / 60;
                double minute1 = arrivalInMinutesPlus30 % 60;
                if (minute1 < 10) {
                    System.out.printf("%.0f:0%.0f hours before the start", hour1, minute1);
                }
            } else {
                double hour1 = Math.floor(arrivalInMinutesPlus30 / 60);
                double minute1 = arrivalInMinutesPlus30 % 60;
                System.out.printf("%f:%f hours before the start", Math.abs(hour1), Math.abs(minute1));
            }
        } else if (arrivalInMinutesPlus30 < 0) {
            if (Math.abs(arrivalInMinutesPlus30) <= 59) {
                System.out.printf("%.0f minutes after the start", Math.abs(arrivalInMinutesPlus30));
            } else {
                double hour1 = arrivalInMinutesPlus30 / 60;
                double minute1 = arrivalInMinutesPlus30 % 60;
                System.out.printf("%.0f:%.0f hours after the start", hour1, minute1);
            }
        } else {
                System.out.printf("%f minutes after the start", arrivalInMinutesPlus30);
            }
        }
    }