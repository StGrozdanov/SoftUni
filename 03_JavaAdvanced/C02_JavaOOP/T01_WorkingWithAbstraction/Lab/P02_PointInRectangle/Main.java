package T01_WorkingWithAbstraction.Lab.P02_PointInRectangle;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] input = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        Rectangle rectangle = new Rectangle(input[0], input[1], input[2], input[3]);
        int numberOfPointsToCheck = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < numberOfPointsToCheck; i++) {
            int[] pointCoordinates = Arrays.stream(scanner.nextLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
            Point point = new Point(pointCoordinates[0], pointCoordinates[1]);
            System.out.println(rectangle.contains(point));
        }
    }
}
