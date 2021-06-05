import java.util.Scanner;

public class KaminoFactory {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int DNALength = Integer.parseInt(scanner.nextLine());
        String input = scanner.nextLine();

        int sample = 0;
        int bestSample = 0;
        int[] bestDna = new int[DNALength];
        int bestSum = 0;
        int maxSequence = 0;
        int bestPosition = 0;

        while (!"Clone them!".equals(input)) {
            sample++;
            String[] dnaAsString = input.split("!+");
            int[] currentDna = new int[DNALength];
            for (int i = 0; i < currentDna.length; i++) {
                currentDna[i] = Integer.parseInt(dnaAsString[i]);
            }

            int sequence = 0;
            int sum = 0;
            int currentPosition = -1;
            int bestCurrentPosition = -1;

            for (int i = 0; i < currentDna.length - 1; i++) {
                if (currentDna[i] == currentDna[i + 1] && currentDna[i] != 0) {
                    sequence += 1;
                    bestCurrentPosition = i;
                    if (bestCurrentPosition > currentPosition) {
                        currentPosition = i;
                    }
                }
                    sum += currentDna[i];
            }
            if (currentDna[currentDna.length - 1] == 1) {
                sum += 1;
            }
            if (sequence > maxSequence) {
                bestDna = currentDna;
                bestSample = sample;
                bestSum = sum;
                maxSequence = sequence;
                bestPosition = currentPosition;
            } else if (sequence == maxSequence && currentPosition < bestPosition || sum > bestSum) {
                bestDna = currentDna;
                bestSample = sample;
                bestSum = sum;
                maxSequence = sequence;
                bestPosition = currentPosition;
            }
            input = scanner.nextLine();
        }
        System.out.printf("Best DNA sample %d with sum: %d.%n", bestSample, bestSum);
        for (int i = 0; i < bestDna.length; i++) {
            System.out.print(bestDna[i] + " ");
        }
    }
}