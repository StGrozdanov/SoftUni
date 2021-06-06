package T06_Generics.Exercise.P03_GenericCountMethodString;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        Box<Double> box = new Box<>();

        for (int i = 0; i < n; i++) {
            double input = Double.parseDouble(scanner.nextLine());
            box.addElements(input);
        }
        double toCompare = Double.parseDouble(scanner.nextLine());

        System.out.println(box.countTheGreaterElements(toCompare));
    }
}
