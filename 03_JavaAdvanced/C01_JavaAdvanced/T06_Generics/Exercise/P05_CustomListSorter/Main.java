package T06_Generics.Exercise.P05_CustomListSorter;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        CustomList<String> list = new CustomList<>();

        while (!"END".equals(input)) {
            String[] tokens = input.split("\\s+");
            String cmd = tokens[0];
            switch (cmd) {
                case "Add":
                    String element = tokens[1];
                    list.add(element);
                    break;
                case "Remove":
                    int index = Integer.parseInt(tokens[1]);
                    list.remove(index);
                    break;
                case "Contains":
                    String elementToFind = tokens[1];
                    System.out.println(list.contains(elementToFind));
                    break;
                case "Swap":
                    int index1 = Integer.parseInt(tokens[1]);
                    int index2 = Integer.parseInt(tokens[2]);
                    list.swap(index1, index2);
                    break;
                case "Greater":
                    String elementToCompare = tokens[1];
                    System.out.println(list.countTheGreaterElements(elementToCompare));
                    break;
                case "Max":
                    System.out.println(list.getMax());
                    break;
                case "Min":
                    System.out.println(list.getMin());
                    break;
                case "Print":
                    System.out.println(list);
                    break;
                case "Sort":
                    list.sort();
            }
            input = scanner.nextLine();
        }
    }
}
