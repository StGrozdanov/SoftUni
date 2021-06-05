package ExamPreparation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ArrayManipulator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String values = scanner.nextLine();
        List<Integer> array = Arrays.stream(values.split("\\s+")).map(Integer::parseInt).
                collect(Collectors.toList());

        String input = scanner.nextLine();

        while (!"end".equals(input)) {
            String[] tokens = input.split("\\s+");
            String command = tokens[0];

            switch (command) {
                case "exchange":
                    int index = Integer.parseInt(tokens[1]);
                    arraySplit(array, index);
                    break;
                case "max":
                    String maxType = tokens[1];
                    findTheMaxElement(array, maxType);
                    break;
                case "min":
                    String minType = tokens[1];
                    findTheMinElement(array, minType);
                    break;
                case "first":
                    int firstCount = Integer.parseInt(tokens[1]);
                    String firstType = tokens[2];
                    findTheFirstGroupOfElements(array, firstCount, firstType);
                    break;
                case "last":
                    int lastCount = Integer.parseInt(tokens[1]);
                    String lastType = tokens[2];
                    findTheLastGroupOfElements(array, lastCount, lastType);
                    break;
            }
            input = scanner.nextLine();
        }
        System.out.print(array);
    }


    private static void findTheLastGroupOfElements(List<Integer> array, int lastCount, String lastType) {

        List<Integer> conditionalArray = new ArrayList<>();

        if (lastType.equals("even")) {
            for (int i = 0; i < array.size(); i++) {
                if (array.get(i) % 2 == 0) {
                    conditionalArray.add(array.get(i));
                }
            }
        } else if (lastType.equals("odd")) {
            for (int i = 0; i < array.size(); i++) {
                if (array.get(i) % 2 != 0) {
                    conditionalArray.add(array.get(i));
                }
            }
        }
        if (lastCount > array.size()) {
            System.out.println("Invalid count");
        } else {
            for (int i = conditionalArray.size() - 1; i > lastCount - 1; i++) {
                conditionalArray.remove(conditionalArray.get(i));
                i--;
            }
            System.out.println(conditionalArray);
        }
    }

    private static void findTheFirstGroupOfElements(List<Integer> array, int firstCount, String firstType) {

        List<Integer> conditionalArray = new ArrayList<>();

        if (firstType.equals("even")) {
            for (int i = 0; i < array.size(); i++) {
                if (array.get(i) % 2 == 0) {
                    conditionalArray.add(array.get(i));
                }
            }
        } else if (firstType.equals("odd")) {
            for (int i = 0; i < array.size(); i++) {
                if (array.get(i) % 2 != 0) {
                    conditionalArray.add(array.get(i));
                }
            }
        }
        if (firstCount > array.size()) {
            System.out.println("Invalid count");
        } else {
            for (int i = 0; i < conditionalArray.size(); i++) {
                if (firstCount - 1 < i) {
                    conditionalArray.remove(conditionalArray.get(i));
                    i--;
                }
            }
            System.out.println(conditionalArray);
        }
    }

    private static void findTheMinElement(List<Integer> array, String minType) {

        int min = 10000;
        int maxElement = -1;

        if (minType.equals("even")) {
            for (int i = 0; i < array.size(); i++) {
                if (array.get(i) % 2 == 0) {
                    if (array.get(i) <= min) {
                        min = array.get(i);
                        maxElement = i;
                    }
                }
            }
        } else if (minType.equals("odd")) {
            for (int i = 0; i < array.size(); i++) {
                if (array.get(i) % 2 != 0) {
                    if (array.get(i) <= min) {
                        min = array.get(i);
                        maxElement = i;
                    }
                }
            }
        }
        if (maxElement < 0) {
            System.out.println("No matches");
        } else {
            System.out.println(maxElement);
        }
    }

    private static void findTheMaxElement(List<Integer> array, String maxType) {

        int max = -1;
        int maxElement = -1;

        if (maxType.equals("even")) {
            for (int i = 0; i < array.size(); i++) {
                if (array.get(i) % 2 == 0) {
                    if (array.get(i) >= max) {
                        max = array.get(i);
                        maxElement = i;
                    }
                }
            }
        } else if (maxType.equals("odd")) {
            for (int i = 0; i < array.size(); i++) {
                if (array.get(i) % 2 != 0) {
                    if (array.get(i) >= max) {
                        max = array.get(i);
                        maxElement = i;
                    }
                }
            }
        }
        if (maxElement < 0) {
            System.out.println("No matches");
        } else {
            System.out.println(maxElement);
        }
    }

    private static void arraySplit(List<Integer> array, int index) {

        if (index >= array.size() || index < 0) {
            System.out.println("Invalid index");
        } else {
            if (index+1 < array.size()) {
                for (int i = 0; i <= array.get(index); i++) {
                    array.add(array.get(i));
                }
                for (int i = 0; i <= array.get(index); i++) {
                    array.remove(array.get(i));
                }
            }
                // 1 2 3 4 5 -> split 2 --> 4 5 1 2 3
            }
        }
    }
