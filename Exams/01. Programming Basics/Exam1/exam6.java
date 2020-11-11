package BasicsExamTest1;

import java.util.Scanner;

public class exam6 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int numberOfCompanies = Integer.parseInt(sc.nextLine());
        String theCompanyWithBiggestCountOfPassengers = "";
        int biggestPassengersCount = 0;

        for (int i = 0; i < numberOfCompanies; i++) {
            String companyName = sc.nextLine();
            int passengers = 0;
            int counterOfFlights = 0;

            for (int j = 0; j < 9999999; j++) {
                String input = sc.nextLine();
                if(input.equalsIgnoreCase("Finish")){
                    break;
                }
                counterOfFlights++;
                passengers = passengers + Integer.parseInt(input);
            }


            passengers = (int) Math.floor(passengers / counterOfFlights);
            System.out.printf("%s: %d passengers.%n", companyName, passengers);

            if (passengers > biggestPassengersCount) {
                theCompanyWithBiggestCountOfPassengers = companyName;
                biggestPassengersCount = passengers;
            }
            counterOfFlights = 0;
        }
        System.out.printf("%s has the most passengers per flight: %d", theCompanyWithBiggestCountOfPassengers, biggestPassengersCount);

    }
}
