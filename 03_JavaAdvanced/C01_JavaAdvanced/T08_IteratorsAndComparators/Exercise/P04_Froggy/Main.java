package T08_IteratorsAndComparators.Exercise.P04_Froggy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] numbers = Arrays.stream(scanner.nextLine().split(", ")).mapToInt(Integer::parseInt).toArray();
        Lake lake = new Lake(numbers);

        List<String> output = new ArrayList<>();
        for (Integer integer : lake) {
            output.add(String.valueOf(integer));
        }
        System.out.println(String.join(", ", output));
    }
}
