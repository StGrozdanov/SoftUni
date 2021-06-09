package T08_IteratorsAndComparators.Exercise.P01_02_ListyIterator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] tokens = scanner.nextLine().split("\\s+");
        String[] data = new String[tokens.length-1];
        for (int i = 0; i < data.length; i++) {
            data[i] = tokens[i+1];
        }

        ListyIterator iterator = new ListyIterator(data);

        String input = scanner.nextLine();

        while (!"END".equals(input)) {
            switch (input) {
                case "Move":
                    if (iterator.iterator().hasNext()){
                        iterator.iterator().next();
                        System.out.println("true");
                    } else {
                        System.out.println("false");
                    }
                    break;
                case "Print":
                    System.out.println(iterator);
                    break;
                case "HasNext":
                    if (iterator.iterator().hasNext()){
                        System.out.println("true");
                    } else {
                        System.out.println("false");
                    }
                    break;
                case "PrintAll":
                    iterator.print();
                    break;
            }
            input = scanner.nextLine();
        }
    }
}
