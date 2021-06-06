package ListsExercise08;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AppendArrays {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] input = scanner.nextLine().split("\\|");
        List<String> result = new ArrayList<>();

        for (int i = input.length - 1; i >= 0 ; i--) {
            String[] temp = input[i].split("\\s+");
            for (String s:temp){
                if (!"".equals(s)) {
                    result.add(s);
                }
            }
        }
        System.out.println(String.join(" ", result));
    }
}
