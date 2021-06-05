package ExamPreparation;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PokemonDonatGo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> sequence = Arrays.stream(scanner.nextLine().split("\\s+")).
                map(Integer::parseInt).collect(Collectors.toList());

        int removedElementsSum = 0;

        while (sequence.size() > 0) {
            int index = Integer.parseInt(scanner.nextLine());
            if (index >= 0 && index <= sequence.size()-1) {
                int removedIndex = sequence.get(index);
                removedElementsSum += removedIndex;
                sequence.remove(index);
                for (int i = 0; i < sequence.size(); i++) {
                    if (sequence.get(i) <= removedIndex) {
                        sequence.set(i, sequence.get(i) + removedIndex);
                    } else {
                        sequence.set(i, sequence.get(i) - removedIndex);
                    }
                }
            } else if (index < 0) {

                int removedIndex = sequence.get(0);
                removedElementsSum += sequence.get(0);
                sequence.remove(0);
                sequence.add(0, sequence.get(sequence.size() - 1));

                for (int i = 0; i < sequence.size(); i++) {
                    if (sequence.get(i) <= removedIndex) {
                        sequence.set(i, sequence.get(i) + removedIndex);
                    } else {
                        sequence.set(i, sequence.get(i) - removedIndex);
                    }
                }
            } else if (index > sequence.size()-1) {
                int removedIndex = sequence.get(sequence.size()-1);
                removedElementsSum += sequence.get(sequence.size() - 1);
                sequence.remove(sequence.size() - 1);
                sequence.add(sequence.get(0));
                for (int i = 0; i < sequence.size(); i++) {
                    if (sequence.get(i) <= removedIndex) {
                        sequence.set(i, sequence.get(i) + removedIndex);
                    } else {
                        sequence.set(i, sequence.get(i) - removedIndex);
                    }
                }
            }
        }
        System.out.println(removedElementsSum);
    }
}
