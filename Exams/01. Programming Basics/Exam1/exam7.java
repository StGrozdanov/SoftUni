package BasicsExamTest1;

import java.util.Scanner;

public class exam7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int companyCount = Integer.parseInt(scanner.nextLine());
        int passengersCount = 0;
        double avgPassengers = 0;
        int flightCounter = 0;

        double biggestPassengers = 0;
        String biggestCompany = "";

        for (int i = 1; i <= companyCount; i++){
            String company = scanner.nextLine();
            while (true){
            String passenger = scanner.nextLine();
            if (passenger.equals("Finish")){
                avgPassengers = 1.0 * passengersCount / flightCounter;
                System.out.printf("%s: %.0f passengers.%n", company, Math.floor(avgPassengers));
                break;
            } else {
                flightCounter ++;
                int passengers = Integer.parseInt(passenger);
                passengersCount += passengers;
            }
            }
            if (avgPassengers > biggestPassengers){
                biggestPassengers = avgPassengers;
                biggestCompany = company;
            }
            passengersCount = 0;
            flightCounter = 0;
        }
        System.out.printf("%s has most passengers per flight: %.0f", biggestCompany, biggestPassengers);
    }
}
