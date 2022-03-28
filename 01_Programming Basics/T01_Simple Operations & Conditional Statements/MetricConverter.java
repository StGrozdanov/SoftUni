package ProgrammingBasics;

import java.util.Scanner;

public class MetricConverter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double num = Double.parseDouble(scanner.nextLine());
        String type = scanner.nextLine();
        String toType = scanner.nextLine();

        if (type.equals("cm")){
            num = num / 100;
        } else if (type.equals("mm")) {
            num = num / 1000;
        }
        if (toType.equals("cm")){
            num = num * 100;
        } else if (toType.equals("mm")){
            num = num * 1000;
        }

        System.out.printf("%.3f", num);
    }
}
