package MidExamPreparation;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class P02_PoisonousPlants {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int initialSize = Integer.parseInt(scanner.nextLine());
        List<Integer> plants = Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt).collect(Collectors.toList());

        ArrayDeque<Integer> stack = new ArrayDeque<>(plants);

        boolean plantsWillSurvive = false;

        int dayCounter = 0;

        while (!plantsWillSurvive) {
            dayCounter++;
            for (int i = plants.size() - 1; i >= 0; i--) {
                if (i - 1 >= 0) {
                    stack.getLast();
                    int lastPlant = plants.get(i);
                    int leftPlant = plants.get(i-1);
                    if (lastPlant > leftPlant) {
                        plants.remove(i);
                    }
                } else {
                    break;
                }
            }
            if (plants.size() == initialSize) {
                plantsWillSurvive = true;
                dayCounter--;
            } else {
                initialSize = plants.size();
            }
        }
        System.out.println(dayCounter);
    }
}