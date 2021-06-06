package T04_StreamsFilesDirectories.Exercise;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.*;

public class WordCount {
    public static void main(String[] args) {

        try {
            BufferedReader text1 = new BufferedReader(new FileReader("C:\\Users\\STGRR\\Desktop\\UNIVERSITY\\3. Java Advanced\\New folder\\08. Streams Files Dimensions FunctionalProgramming.DefiningClasses.DefiningClasses.Generics.Lab.Generics.Lab.Generics.Exercise\\04. Java-Advanced-Files-and-Streams-Exercises-Resources\\text.txt"));
            BufferedReader text2 = new BufferedReader(new FileReader("C:\\Users\\STGRR\\Desktop\\UNIVERSITY\\3. Java Advanced\\New folder\\08. Streams Files Dimensions FunctionalProgramming.DefiningClasses.DefiningClasses.Generics.Lab.Generics.Lab.Generics.Exercise\\04. Java-Advanced-Files-and-Streams-Exercises-Resources\\words.txt"));
            PrintWriter output = new PrintWriter("C:\\Users\\STGRR\\Desktop\\UNIVERSITY\\3. Java Advanced\\New folder\\08. Streams Files Dimensions FunctionalProgramming.DefiningClasses.DefiningClasses.Generics.Lab.Generics.Lab.Generics.Exercise\\04. Java-Advanced-Files-and-Streams-Exercises-Resources\\results.txt");

            String[] checkers = text2.readLine().split("\\s+");
            Map<String, Integer> results = new LinkedHashMap<>();

            for (String word : checkers) {
                results.putIfAbsent(word, 0);
            }

            String[] textToCheck = text1.readLine().split("\\s+");

            for (String word : textToCheck) {
                if (results.containsKey(word)){
                    int oldValue = results.get(word);
                    results.put(word, oldValue + 1);
                }
            }

            results.entrySet().stream().sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                    .forEach(x -> output.printf("%s - %d%n", x.getKey(), x.getValue()));
            output.close();
            text1.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
