import java.util.Scanner;

public class MaxSequenceOfEqualElements {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] input = scanner.nextLine().split(" ");
        int[] n = new int[input.length];

        for (int i = 0; i < n.length; i++) {
            n[i] = Integer.parseInt(input[i]);
        }

        int counter = 1;
        int biggestCounter = 0;
        int longest = 0;

        for (int i = 0; i < n.length - 1; i++) {
                if (n[i] == n[i+1]){
                    counter++;
                    if (counter > biggestCounter){
                        biggestCounter = counter;
                        longest = n[i];
                    }
                    } else {
                    counter = 1;
                }
            }
        for (int i = 0; i < biggestCounter; i++) {
            System.out.print(longest + " ");
        }
    }
}
