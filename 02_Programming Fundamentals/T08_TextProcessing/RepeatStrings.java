package TextProcessing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class RepeatStrings {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> input = Arrays.stream(scanner.nextLine().split("\\s+")).collect(Collectors.toList());
        List<String> output = new ArrayList<>();
        for (String s : input) {
            for (int j = 0; j < s.length(); j++) {
                output.add(s);
            }
        }
        System.out.println(String.join("", output));
    }
}
