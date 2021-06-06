package ListsExercise08;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ChangeList {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> list = Arrays.stream(scanner.nextLine().split("\\s+")).map(Integer::parseInt).collect(Collectors.toList());
        String input = scanner.nextLine();

        while(!"end".equals(input)){
            String[] command = input.split("\\s+");
            if ("Delete".equals(command[0])){
                int elementToRemove = Integer.parseInt(command[1]);
                if (list.contains(elementToRemove)){
                    list.removeAll(Collections.singleton(elementToRemove));
                }
            } else {
                int elementToAdd = Integer.parseInt(command[1]);
                int index = Integer.parseInt(command[2]);
                if (index >= 0 && index < list.size()) {
                    list.add(index, elementToAdd);
                }
            }
            input = scanner.nextLine();
        }
        for (int finalList:list){
            System.out.print(finalList + " ");
        }
    }
}
