package T04_InterfacesAndAbstraction.Lab.P06_Ferrari;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Ferrari ferrari = new Ferrari(scanner.nextLine());

        System.out.println(ferrari);
    }
}
