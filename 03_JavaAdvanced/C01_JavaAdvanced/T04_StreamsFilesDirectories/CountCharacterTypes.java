package T04_StreamsFilesDirectories.Exercise;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CountCharacterTypes {
    public static void main(String[] args) {

        try {
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\STGRR\\Desktop\\UNIVERSITY\\3. Java Advanced\\New folder\\08. Streams Files Dimensions FunctionalProgramming.DefiningClasses.DefiningClasses.Generics.Lab.Generics.Lab.Generics.Exercise\\04. Java-Advanced-Files-and-Streams-Exercises-Resources\\input.txt"));
            PrintWriter output = new PrintWriter("C:\\Users\\STGRR\\Desktop\\UNIVERSITY\\3. Java Advanced\\New folder\\08. Streams Files Dimensions FunctionalProgramming.DefiningClasses.DefiningClasses.Generics.Lab.Generics.Lab.Generics.Exercise\\04. Java-Advanced-Files-and-Streams-Exercises-Resources\\output.txt");
            String line = reader.readLine();
            List<Character> vowels = new ArrayList<>(Arrays.asList('a', 'e', 'o', 'u', 'i'));
            List<Character> punctuation = new ArrayList<>(Arrays.asList('!', ',', '.', '?'));
            int vowelsCounter = 0;
            int punctuationCounter = 0;
            int consonantsCounter = 0;

            while (line != null){
                for (int i = 0; i < line.length(); i++) {
                    char current = line.charAt(i);
                    if (current != 32){
                        if (vowels.contains((current))){
                            vowelsCounter++;
                        } else if (punctuation.contains(current)){
                            punctuationCounter++;
                        } else {
                            consonantsCounter++;
                        }
                    }
                }
                line = reader.readLine();
            }
            output.printf("Vowels: %d%n", vowelsCounter);
            output.printf("Consonants: %d%n", consonantsCounter);
            output.printf("Punctuation: %d", punctuationCounter);
            output.close();
            reader.close();
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
