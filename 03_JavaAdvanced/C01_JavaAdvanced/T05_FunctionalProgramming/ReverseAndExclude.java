package T05_FunctionalProgramming.Exercise;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ReverseAndExclude {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> numbers = Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt).collect(Collectors.toList());
        int divisor = Integer.parseInt(scanner.nextLine());

        Collections.reverse(numbers);
        List<String> collect = numbers.stream().filter(e -> e % divisor != 0)
                .map(String::valueOf).collect(Collectors.toList());

        System.out.println(String.join(" ", collect));

    }
}
