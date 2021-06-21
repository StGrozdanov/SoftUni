package MidExamPreparation.P24_LinkedListTraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());
        GenericLinkedList<Integer> customList = new GenericLinkedList<>();
        for (int i = 0; i < n; i++) {
            String[] tokens = reader.readLine().split("\\s+");
            String cmd = tokens[0];
            int number = Integer.parseInt(tokens[1]);
            switch (cmd) {
                case "Add":
                    customList.add(number);
                    break;
                case "Remove":
                    customList.remove(number);
                    break;
            }
        }
        customList.getSize();
        for (Integer integer : customList) {
            System.out.print(integer + " ");
        }
    }
}
