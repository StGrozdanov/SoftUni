package Methods05;

import java.util.Scanner;

public class CalculateRectangleArea {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double width = Double.parseDouble(scanner.nextLine());
        double height = Double.parseDouble(scanner.nextLine());

        double area = rectangleArea(width, height);

        System.out.printf("%.0f", area);

    }
    private static double rectangleArea(double width, double height){
        return width * height;
    }
}
