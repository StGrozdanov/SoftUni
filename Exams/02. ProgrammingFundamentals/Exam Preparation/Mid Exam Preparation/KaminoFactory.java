package ExamPreparation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class KaminoFactory {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int sequenceLength = Integer.parseInt(scanner.nextLine());
        List<Integer> bestDna = new ArrayList<>();

        int sequenceCounter = 0;
        int bestSequenceCounter = 0;
        int startingIndex = 0;
        int bestStartingIndex = 0;
        int sum = 0;
        int bestSum = 0;
        int sequence = 0;
        int bestSequence = 0;

        String input = scanner.nextLine();

        while (!"Clone them!".equals(input)) {
            sequence ++;
            String[] dnaString = input.split("!+");
            List<Integer> dna = new ArrayList<>();

            for (int i = 0; i < dnaString.length; i++) {
                dna.add(Integer.parseInt(dnaString[i]));
            }
            for (int i = 0; i < dna.size(); i++) {
                if (dna.get(i) == 1) {
                    sum += dna.get(i);
                }
            }
            for (int i = 0; i < dna.size() - 1; i++) {
                if (dna.get(i) == 1 && dna.get(i + 1) == 1) {
                    sequenceCounter++;
                }
            }
            for (int i = 0; i < dna.size()-1; i++) {
                if (dna.get(i) == 1 && dna.get(i + 1) == 1) {
                    startingIndex = i;
                    break;
                }
            }
            if (sequenceCounter > bestSequenceCounter) {
                bestSequenceCounter = sequenceCounter;
                bestDna = dna;
                bestSequence = sequence;
                bestSum = sum;
                bestStartingIndex = startingIndex;
            }
            else if (sequenceCounter == bestSequenceCounter) {
                  if (startingIndex < bestStartingIndex) {
                    bestStartingIndex = startingIndex;
                    bestSequenceCounter = sequenceCounter;
                    bestDna = dna;
                    bestSequence = sequence;
                    bestSum = sum;
                } else if (startingIndex == bestStartingIndex) {
                    if (sum > bestSum) {
                        bestStartingIndex = startingIndex;
                        bestSequenceCounter = sequenceCounter;
                        bestSum = sum;
                        bestDna = dna;
                        bestSequence = sequence;
                    }
                }
            } if (sequenceCounter == 0){
                bestSequenceCounter = sequenceCounter;
                bestDna = dna;
                bestSequence = 1;
                bestSum = sum;
                bestStartingIndex = startingIndex;
            }
                sequenceCounter = 0;
                sum = 0;
                input = scanner.nextLine();
            }
        System.out.printf("Best DNA sample %d with sum: %d.%n", bestSequence, bestSum);
        for (int dna:bestDna){
            System.out.print(dna + " ");
        }
        }
    }
