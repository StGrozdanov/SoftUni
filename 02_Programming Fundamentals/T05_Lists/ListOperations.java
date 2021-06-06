package ListsExercise08;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ListOperations {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> list = Arrays.stream(scanner.nextLine().split("\\s+")).map(Integer::parseInt).collect(Collectors.toList());
        String input = scanner.nextLine();

        while (!"End".equals(input)) {
            String[] command = input.split("\\s+");
            if ("Add".equals(command[0])) {
                int numberToAdd = Integer.parseInt(command[1]);
                list.add(numberToAdd);
            } else if ("Insert".equals(command[0])) {
                int numberToInsert = Integer.parseInt(command[1]);
                int index = Integer.parseInt(command[2]);
                if (index >= 0 && index < list.size()) {
                    list.add(index, numberToInsert);
                } else {
                    System.out.println("Invalid index");
                }
            } else if ("Remove".equals(command[0])) {
                int indexToRemove = Integer.parseInt(command[1]);
                if (indexToRemove >= 0 && indexToRemove < list.size()) {
                    list.remove(indexToRemove);
                } else {
                    System.out.println("Invalid index");
                }
            } else if ("Shift".equals(command[0])) {
                int count = Integer.parseInt(command[2]);
                int counter = 0;
                if ("left".equals(command[1])){
                while (counter < count) {
                    counter++;
                    list.add(list.get(0));
                    list.remove(list.get(0));
                }
                } else {
                    while (counter < count) {
                    counter++;
                    list.add(0, list.get(list.size()-1));
                    list.remove(list.size()-1);
                }
                }
            }
            input = scanner.nextLine();
        }
        for (int finalList : list) {
            System.out.print(finalList + " ");
        }
    }
}
