package ProgrammingBasics;

import java.util.Scanner;

public class TimePlus15Minutes {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int hour = Integer.parseInt(scanner.nextLine());
        int minutes = Integer.parseInt(scanner.nextLine());

        int hourInMinutes = hour * 60 + minutes;
        int hourPlus15 = hourInMinutes + 15;
        int finalHour = hourPlus15 / 60;
        int finalMinutes = hourPlus15 % 60;

        if(finalHour > 23){
            finalHour = 0;
        }
        System.out.printf("%d:%02d", finalHour, finalMinutes);
    }
}


