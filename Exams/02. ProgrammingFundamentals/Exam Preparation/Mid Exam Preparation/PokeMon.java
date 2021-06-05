package ExamPreparation;

import java.util.Scanner;

public class PokeMon {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        int m = Integer.parseInt(scanner.nextLine());
        int y = Integer.parseInt(scanner.nextLine());

        int pokeCounter = 0;
        double percentFactor = n * 0.5;

        while (n>=m){
            n -= m;
            pokeCounter ++;
            if (n == percentFactor){
                if (y>=1 && n > 1)
                n = n/y;
            }
        }
        System.out.println(n);
        System.out.println(pokeCounter);
    }
}
