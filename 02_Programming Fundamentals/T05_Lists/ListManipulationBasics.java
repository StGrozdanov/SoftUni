package Lists07;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ListManipulationBasics {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> list = Arrays.stream(scanner.nextLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());

        String input = scanner.nextLine();

        while(!"end".equals(input)){

            String[] array = input.split(" ");
            String command = array[0];
            int argument = Integer.parseInt(array[1]);

            switch (command){
                case "Add":
                    list.add(argument);
                    break;
                case "Remove":
                    list.remove(Integer.valueOf(argument));
                    break;
                case "RemoveAt":
                    list.remove(argument);
                    break;
                case "Insert":
                    int index = Integer.parseInt(array[2]);
                    list.add(index, argument);
                    break;
            }
            input = scanner.nextLine();
        }
        System.out.println(list.toString().replaceAll("[\\[\\],]", ""));
        }
    }
