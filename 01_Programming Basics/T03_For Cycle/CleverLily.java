package ProgrammingBasics.ForCycle;

import java.util.Scanner;

public class CleverLily {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int age = Integer.parseInt(scanner.nextLine());
        double goalPrice = Double.parseDouble(scanner.nextLine());
        int toyPrice = Integer.parseInt(scanner.nextLine());

        int money = 0;
        int toys = 0;

        for (int i = 1; i <= age; i++){
            if (i % 2 == 0){
                money = (((i/2)*10)+money)-1;
            } else {
                toys = (i/2)+1;
            }
        } int toyMoney = toys * toyPrice;
        int totalMoney = toyMoney + money;
        if (totalMoney >= goalPrice){
            double MoneyLeft = totalMoney - goalPrice;
            System.out.printf("Yes! %.2f", MoneyLeft);
        } else {
            double MoneyLeft = goalPrice - totalMoney;
            System.out.printf("No! %.2f", MoneyLeft);
        }
    }
}
