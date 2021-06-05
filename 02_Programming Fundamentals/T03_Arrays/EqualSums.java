import java.util.Scanner;

public class EqualSums {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] input = scanner.nextLine().split(" ");
        int[] a = new int[input.length];

        for (int i = 0; i < a.length; i++) {
            a[i] = Integer.parseInt(input[i]);
        }

        boolean equals = false;

        for (int i = 0; i < a.length; i++) {
            int leftSum = 0;
            int rightSum = 0;
            for (int j = i - 1; j >= 0; j--) {
                leftSum += a[j];
            }
            for (int j = i + 1; j < a.length; j++) {
                rightSum += a[j];
            }
            if (leftSum == rightSum){
                equals = true;
                System.out.println(i);
                break;
            }
        }
        if (!equals){
            System.out.println("no");
        }
    }
}
