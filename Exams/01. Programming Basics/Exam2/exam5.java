package BasicsExamTest2;

import java.util.Scanner;

public class exam5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int target = Integer.parseInt(scanner.nextLine());


        int price = 0;
        int income = 0;

        while (income < target){

            String type = scanner.nextLine();

            if (type.equals("closed")){
                break;
            } else {

                switch (type) {
                    case "color":
                        type = scanner.nextLine();
                        switch (type) {
                            case "touch up":
                                price = 20;
                                break;
                            case "full color":
                                price = 30;
                                break;
                        }
                        break;
                    case "haircut":
                        type = scanner.nextLine();
                        switch (type) {
                            case "mens":
                                price = 15;
                                break;
                            case "ladies":
                                price = 20;
                                break;
                            case "kids":
                                price = 10;
                                break;
                        }
                        break;
                }
            }
            income += price;
        }
        if (income >= target){
            System.out.println("You have reached your target for the day!");
        } else {
            int moneyNeeded = target - income;
            System.out.printf("Target not reached! You need %dlv. more.%n", moneyNeeded);
        }
        System.out.printf("Earned money: %dlv.", income);
    }
}
