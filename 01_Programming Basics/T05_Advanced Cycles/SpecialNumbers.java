package ProgrammingBasics.AdvancedCycles.Exercise;

import java.util.Scanner;

public class SpecialNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int num = Integer.parseInt(scanner.nextLine());
        int charCounter = 0;

        for (int i = 1111; i <= 9999; i++){
            String currentNum = "" + i;
            for (int j = 0; j < 4; j++){
                int position = Integer.parseInt("" + currentNum.charAt(j));
                if (position == 0){
                    charCounter = 0;
                    break;
                }
                if (num % position == 0) {
                    charCounter++;
                } else {
                    charCounter = 0;
                } if (charCounter == 4){
                        System.out.print(i + " ");
                        charCounter = 0;
                        break;
                    }
                }
                }
            }
        }
