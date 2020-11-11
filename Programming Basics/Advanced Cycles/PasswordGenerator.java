package ProgrammingBasics.AdvancedCycles.Exercise;

import java.util.Scanner;

public class PasswordGenerator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        int L = Integer.parseInt(scanner.nextLine());

        for (int firstNum = 1; firstNum < n; firstNum++){
            for (int secondNum = 1; secondNum < n; secondNum++){
                for (char firstChar = 'a'; firstChar < 'a' + L; firstChar++){
                for (char secondChar = 'a'; secondChar < 'a' + L; secondChar++){
                    for (int thirdNum = 0; thirdNum <= n; thirdNum++){
                        if (thirdNum > firstNum && thirdNum > secondNum){
                            System.out.printf("%d%d%c%c%d ", firstNum, secondNum, firstChar, secondChar, thirdNum);
                        }
                    }
                }
                }
            }
        }
    }
}
