package ProgrammingBasics.WhileCycle;

import java.util.Scanner;

public class Moving {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int width = Integer.parseInt(scanner.nextLine());
        int height = Integer.parseInt(scanner.nextLine());
        int length = Integer.parseInt(scanner.nextLine());
        int quadrature = width * height * length;

        while (quadrature > 0) {
            String boxes = scanner.nextLine();
            if (boxes.equals("Done")){
                break;
            }
            int boxCounter = Integer.parseInt(boxes);
            quadrature = quadrature - boxCounter;
        } if (quadrature <= 0){
            System.out.printf("No more free space! You need %d Cubic meters more.", Math.abs(quadrature));
        } else {
            System.out.printf("%d Cubic meters left.", quadrature);
        }
    }
}
