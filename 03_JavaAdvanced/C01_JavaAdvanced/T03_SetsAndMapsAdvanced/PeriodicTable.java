package T03_SetsAndMapsAdvanced.Exercise;

import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class PeriodicTable {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        Set<String> unique = new TreeSet<>();

        for (int i = 0; i < n; i++) {
            String[] tokens = scanner.nextLine().split("\\s+");
            for (int j = 0; j < tokens.length; j++) {
                unique.add(tokens[j]);
            }
        }
        for (String s : unique) {
            System.out.print(s + " ");
        }
    }
}
