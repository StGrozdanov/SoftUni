package ArraysExercise;

import java.util.Scanner;

public class CommonElements {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] array1 = scanner.nextLine().split(" ");
        String[] array2 = scanner.nextLine().split(" ");

        for (String array2Elements : array2){
            for (String array1Elements : array1){
                if (array2Elements.equals(array1Elements)){
                    System.out.print(array2Elements + " ");
                }
            }
        }
    }
}
