package ListsExercise08;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Train {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> passengersList = Arrays.stream(scanner.nextLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
        int wagonCapacity = Integer.parseInt(scanner.nextLine());
        String input = scanner.nextLine();

        while(!"end".equals(input)){
            String[] command = input.split(" ");
            if (command[0].equals("Add")){
                int newWagon = Integer.parseInt(command[1]);
                passengersList.add(newWagon);
            } else {
                int addCount = Integer.parseInt(command[0]);
                findWagonToFitPassengers(passengersList, wagonCapacity, addCount);
            }
            input = scanner.nextLine();
        }
        for (int train:passengersList){
            System.out.print(train + " ");
        }
    }

    private static void findWagonToFitPassengers(List<Integer> passengersList, int wagonCapacity, int addCount) {
        for (int i = 0; i < passengersList.size(); i++) {
            if (passengersList.get(i) < wagonCapacity && passengersList.get(i) + addCount <= wagonCapacity){
                passengersList.set(i, passengersList.get(i) + addCount);
                break;
            }
        }
    }
}
