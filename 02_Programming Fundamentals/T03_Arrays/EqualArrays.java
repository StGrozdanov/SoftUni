package Arrays;

import java.util.Scanner;

public class EqualArrays {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] arrayAsString1 = scanner.nextLine().split(" ");
        int[] arrayAsNum1 = new int[arrayAsString1.length];

        for (int i = 0; i < arrayAsNum1.length; i++) {
            arrayAsNum1[i] = Integer.parseInt(arrayAsString1[i]);
        }

        String[] arrayAsString2 = scanner.nextLine().split(" ");
        int[] arrayAsNum2 = new int[arrayAsString2.length];

        for (int i = 0; i < arrayAsNum2.length; i++) {
            arrayAsNum2[i] = Integer.parseInt(arrayAsString2[i]);
        }

        int sum = 0;
        boolean notIdentical = false;

            for (int i = 0; i < arrayAsNum1.length; i++){
                if (arrayAsNum1[i] != arrayAsNum2[i]){
                    System.out.printf("Arrays are not identical. Found difference at %d index.", i);
                    notIdentical = true;
                    break;
                }
                else {
                    sum += arrayAsNum1[i];
                }
            }
            if (!notIdentical) {
                System.out.printf("Arrays are identical. Sum: %d", sum);
            }
        }
    }
