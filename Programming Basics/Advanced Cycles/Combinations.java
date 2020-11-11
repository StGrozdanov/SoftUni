package ProgrammingBasics.AdvancedCycles;

import java.util.Scanner;

public class Combinations {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int num = Integer.parseInt(scanner.nextLine());
        int sum = 0;
        int counter = 0;

        for (int i = 0; i <= num; i++){
            for (int j = 0; j <= num; j++){
                for (int f = 0; f <= num; f++){
                    sum = i + j + f;
                    if (sum == num){
                        counter++;
                    }
                }
            }
        }
        System.out.println(counter);
    }
}
