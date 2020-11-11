package ProgrammingBasics.ForCycle.Exercise;

import java.util.Scanner;

public class Salary {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int tabs = Integer.parseInt(scanner.nextLine());
        int salary = Integer.parseInt(scanner.nextLine());

        int facebook = 0;
        int instagram = 0;
        int reddit = 0;

        int fbBadBoy = 0;
        int igBadBoy = 0;
        int rtBadBoy = 0;

        int totalSalary = 0;


        for (int i = 1; i <= tabs; i++){
            String website = scanner.nextLine();
            if (website.equals("Facebook")){
                facebook++;
                fbBadBoy = facebook * 150;
            } if (website.equals("Instagram")){
                instagram++;
                igBadBoy = instagram * 100;
            } if (website.equals("Reddit")){
                reddit++;
                rtBadBoy = reddit * 50;
            }
            totalSalary = salary - fbBadBoy - igBadBoy - rtBadBoy;
            if (totalSalary <= 0){
                break;
            }
        } if (totalSalary <= 0){
            System.out.println("You have lost your salary.");
        } else {
            System.out.println(totalSalary);
        }
    }
}
