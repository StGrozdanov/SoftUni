package ProgrammingBasics.AdvancedCycles;

import java.util.Scanner;

public class Projection {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String movie = scanner.nextLine();
        int standardCounter = 0;
        int studentCounter = 0;
        int kidsCounter = 0;
        int ticketCounter = 0;
        int totalTickets = 0;

        while (!movie.equals("Finish")){
            int freeSpace = Integer.parseInt(scanner.nextLine());
            for (int i = 0; i < freeSpace; i++){
                String ticketType = scanner.nextLine();
                if (ticketType.equals("End")){
                    break;
                } else if (ticketType.equals("student")){
                    studentCounter++;
                    ticketCounter++;
                } else if (ticketType.equals("standard")){
                    standardCounter++;
                    ticketCounter++;
                } else if (ticketType.equals("kid")){
                    kidsCounter++;
                    ticketCounter++;
                }
                totalTickets ++;
            }
            double movieCapacity = 1.0 * ticketCounter / freeSpace * 100;
            System.out.printf("%s - %.2f%% full.%n", movie, movieCapacity);
            ticketCounter = 0;
            movie = scanner.nextLine();
        }
        System.out.printf("Total tickets: %d%n", totalTickets);
        System.out.printf("%.2f%% student tickets.%n", 1.0 * studentCounter / totalTickets * 100);
        System.out.printf("%.2f%% standard tickets.%n", 1.0 * standardCounter / totalTickets * 100);
        System.out.printf("%.2f%% kids tickets.", 1.0 * kidsCounter / totalTickets * 100);
    }
}
