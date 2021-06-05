package ArraysExercise;

import java.util.Scanner;

public class TopIntegers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] input = scanner.nextLine().split(" ");
        int[] num = new int[input.length];

        for (int i = 0; i < num.length; i++) {
            num[i] = Integer.parseInt(input[i]);
        }
        boolean bigger = false;
        for (int i = 0; i < num.length - 1; i++) {
                for (int j = 0; j < num.length; j++) {
                    if (num[i] > num[i+1] && num[i] > num[num.length-1]){
                        bigger = true;
                    } else {
                        break;
                    }
            }
                if (bigger) {
                    System.out.print(num[i] + " ");
                    bigger = false;
                }
        }
        System.out.print(num[num.length - 1]);
        }
    }