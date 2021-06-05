package SameOlShit;

import java.util.Scanner;

public class Login {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String username = scanner.nextLine();

        String reverse = "";

        for (int i = username.length() - 1; i >= 0; i--){
            reverse = reverse + username.charAt(i);
        }

        String input = scanner.nextLine();

        int inputCounter = 0;

        while (!reverse.equals(input)){
            inputCounter++;
            if (inputCounter == 4){
                break;
            } else {
                System.out.println("Incorrect password. Try again.");
            }
            input = scanner.nextLine();
        }
        if (inputCounter == 4){
            System.out.printf("User %s blocked!", username);
        } else {
            System.out.printf("User %s logged in.", username);
        }
    }
}
