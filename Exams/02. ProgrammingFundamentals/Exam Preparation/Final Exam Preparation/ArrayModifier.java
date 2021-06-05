package exam01;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ArrayModifier {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> numbers = Arrays.stream(scanner.nextLine().split("\\s+")).
                map(Integer::parseInt).collect(Collectors.toList());

        String input = scanner.nextLine();

        while (!"end".equals(input)) {
            String[] tokens = input.split("\\s+");
            String cmd = tokens[0];
            switch (cmd) {
                case "swap":
                    int index1 = Integer.parseInt(tokens[1]);
                    int index2 = Integer.parseInt(tokens[2]);

                    if (index1 >= 0 && index1 < numbers.size() && index2 >= 0 && index2 < numbers.size()) {
                        int firstElementToSwap = numbers.get(index1);
                        int secondElementToSwap = numbers.get(index2);

                        numbers.set(index1, secondElementToSwap);
                        numbers.set(index2, firstElementToSwap);
                    }
                    break;
                case "multiply":
                    int multiIndex1 = Integer.parseInt(tokens[1]);
                    int multiIndex2 = Integer.parseInt(tokens[2]);

                    if (multiIndex1 >= 0 && multiIndex1 < numbers.size() && multiIndex2 >= 0 && multiIndex2 < numbers.size()) {
                        int firstElementToMultiply = numbers.get(multiIndex1);
                        int secondElementToMultiply = numbers.get(multiIndex2);

                        int product = firstElementToMultiply * secondElementToMultiply;

                        numbers.set(multiIndex1, product);
                    }
                    break;
                case "decrease":
                    for (int i = 0; i < numbers.size(); i++) {
                        numbers.set(i, numbers.get(i) - 1);
                    }
                    break;
            }
            input = scanner.nextLine();
        }
        System.out.println(numbers.toString().replaceAll("[\\[\\]]", ""));
        }
    }
