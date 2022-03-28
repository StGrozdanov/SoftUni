package ProgrammingBasics.ForCycle.Exercise;

import java.util.Scanner;

public class HalfSumElement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int number = Integer.parseInt(scanner.nextLine());
        int MAX = Integer.MIN_VALUE;
        int sum = 0;
        int sumWithoutMAX = 0;

        for (int i = 1; i <= number; i++){
            int number2 = Integer.parseInt(scanner.nextLine());
            sum += number2;
          if (MAX < number2){
              MAX = number2;
          }
          } sumWithoutMAX = sum - MAX;
        if (MAX == sumWithoutMAX){
            System.out.println("Yes");
            System.out.println("Sum = " + sumWithoutMAX);
        } else {
            int diff = Math.abs(MAX - sumWithoutMAX);
            System.out.println("No");
            System.out.println("Diff = " + diff);
        }
    }
}
