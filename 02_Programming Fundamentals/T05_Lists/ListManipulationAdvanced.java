package Lists07;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ListManipulationAdvanced {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> list = Arrays.stream(scanner.nextLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());

        String input = scanner.nextLine();

        while (!"end".equals(input)) {

            String[] array = input.split(" ");
            String command = array[0];

            switch (command) {
                case "Contains":
                    int number = Integer.parseInt(array[1]);
                    if (list.contains(number)){
                        System.out.println("Yes");
                    } else {
                        System.out.println("No such number");
                    }
                    break;
                case "Print":
                    if ("even".equals(array[1])){
                        printEven(list);
                    } else {
                        printOdd(list);
                    }
                    break;
                case "Get":
                    int sum = 0;
                    for (int i = 0; i < list.size(); i++) {
                        sum += list.get(i);
                    }
                    System.out.println(sum);
                    break;
                case "Filter":
                    String condition = array[1];
                    int num = Integer.parseInt(array[2]);
                    FilterByCondition(condition, num, list);
                    break;
            }
            input = scanner.nextLine();
        }
    }

    private static void FilterByCondition(String condition, int num, List<Integer> list) {

        for (int i = 0; i < list.size(); i++) {
            switch (condition){
                case "<":
                    if (list.get(i) < num){
                        System.out.print(list.get(i) + " ");
                    }
                    break;
                case ">":
                    if (list.get(i) > num){
                        System.out.print(list.get(i) + " ");
                    }
                    break;
                case "<=":
                    if (list.get(i) <= num){
                        System.out.print(list.get(i) + " ");
                    }
                    break;
                case ">=":
                    if (list.get(i) >= num){
                        System.out.print(list.get(i) + " ");
                    }
                    break;
        }
        }
        System.out.println();
    }

    private static void printOdd(List<Integer> list) {

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) % 2 != 0){
                System.out.print(list.get(i) + " ");
            }
        }
        System.out.println();
    }

    private static void printEven(List<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) % 2 == 0){
                System.out.print(list.get(i) + " ");
            }
        }
        System.out.println();
    }
}

