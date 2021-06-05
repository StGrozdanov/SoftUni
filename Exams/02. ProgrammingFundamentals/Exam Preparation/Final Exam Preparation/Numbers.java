package exam01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Numbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> numbers = Arrays.stream(scanner.nextLine().split("\\s+")).map(Integer::parseInt).
                collect(Collectors.toList());

        int sum = 0;
        for (Integer number : numbers) {
            sum += number;
        }
        double averageNum = 1.0 * sum / numbers.size();

        List<Integer> biggestNumbers = new ArrayList<>();

        for (Integer number : numbers) {
            if (number > averageNum) {
                biggestNumbers.add(number);
            }
        }
        biggestNumbers.sort((p1, p2) -> Double.compare(p2, p1));

        List<Integer> top5 = new ArrayList<>();

        int counter = 0;
        for (int i = 0; i < biggestNumbers.size(); i++) {
            counter++;
            if (counter > 5) {
                break;
            } else {
                top5.add(biggestNumbers.get(i));
            }
        }
        if (top5.size() == 0) {
            System.out.println("No");
        } else {
            for (int top : top5) {
                System.out.print(top + " ");
            }
        }
    }
}
