package T06_Generics.Exercise.P02_GenericSwapMethod;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        GenericSwapMethod<String> toSwap = new GenericSwapMethod<>();

        for (int i = 0; i < n; i++) {
            String input = scanner.nextLine();
            toSwap.addElement(input);
        }

        String[] tokens = scanner.nextLine().split("\\s+");
        int index1 = Integer.parseInt(tokens[0]);
        int index2 = Integer.parseInt(tokens[1]);
        toSwap.swap(index1, index2);

        System.out.println(toSwap);
    }
}
