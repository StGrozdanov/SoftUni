package BasicsExamTest2;

import java.util.Scanner;

public class exam1 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int groupPeople = Integer.parseInt(scanner.nextLine());
        int stay = Integer.parseInt(scanner.nextLine());
        int cards = Integer.parseInt(scanner.nextLine());
        int tickets = Integer.parseInt(scanner.nextLine());

        double stayPrice = stay * 20;
        double cardsPrice = cards * 1.6;
        double ticketsPrice = tickets * 6;

        double totalPrice = ((stayPrice + cardsPrice + ticketsPrice) * groupPeople) * 1.25;

        System.out.printf("%.2f", totalPrice);

    }
}
