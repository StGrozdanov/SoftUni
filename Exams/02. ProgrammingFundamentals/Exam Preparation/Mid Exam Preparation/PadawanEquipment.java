package ExamPreparation;

import java.util.Scanner;

public class PadawanEquipment {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double moneyAmount = Double.parseDouble(scanner.nextLine());
        int studentsCount = Integer.parseInt(scanner.nextLine());
        double lightSaberPrice = Double.parseDouble(scanner.nextLine());
        double robesPrice = Double.parseDouble(scanner.nextLine());
        double beltsPrice = Double.parseDouble(scanner.nextLine());

        double lightSabersToBuy = Math.ceil(studentsCount * 1.1);
        double freeBelts = 0;

        if (studentsCount >= 6){
            if (studentsCount % 6 == 0) {
                freeBelts = 1.0 * studentsCount / 6;
            }
            else {
                freeBelts = Math.floor(1.0 * studentsCount / 6);
            }
        }

        double lightSaberMoney = lightSabersToBuy * lightSaberPrice;
        double robesMoney = studentsCount * robesPrice;
        double beltsMoney = (studentsCount - freeBelts)  * beltsPrice;

        double totalMoneyNeeded = lightSaberMoney + robesMoney + beltsMoney;

        if (totalMoneyNeeded <= moneyAmount){
            System.out.printf("The money is enough - it would cost %.2flv.%n", totalMoneyNeeded);
        } else {
            double shortOfMoney = totalMoneyNeeded - moneyAmount;
            System.out.printf("Ivan Cho will need %.2flv more.%n", shortOfMoney);

        }

    }
}
