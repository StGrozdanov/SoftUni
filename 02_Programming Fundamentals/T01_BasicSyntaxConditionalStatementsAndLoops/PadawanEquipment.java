package SameOlShit;

import java.util.Scanner;

public class PadawanEquipment {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double capital = Double.parseDouble(scanner.nextLine());
        double students = Double.parseDouble(scanner.nextLine());
        double lightSabersPrice = Double.parseDouble(scanner.nextLine());
        double robesPrice = Double.parseDouble(scanner.nextLine());
        double beltsPrice = Double.parseDouble(scanner.nextLine());

        double sabersCount = Math.ceil(students * 1.1);
        double freeBelts = 0;

        if (students >= 6){
            freeBelts = Math.floor(students / 6);
        }
        double beltsCount = students - freeBelts;

        double equipmentPrice = (students * robesPrice) + (sabersCount * lightSabersPrice) + (beltsCount * beltsPrice);

        if (equipmentPrice <= capital){
            System.out.printf("The money is enough - it would cost %.2flv.", equipmentPrice);
        } else {
            double moneyNeeded = equipmentPrice - capital;
            System.out.printf("Ivan Cho will need %.2flv more.", moneyNeeded);
        }
    }
}
