package T04_StreamsFilesDirectories.Exercise;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SumBytes {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\STGRR\\Desktop\\UNIVERSITY\\3. Java Advanced\\New folder\\08. Streams Files Dimensions FunctionalProgramming.DefiningClasses.DefiningClasses.Generics.Lab.Generics.Lab.Generics.Exercise\\04. Java-Advanced-Files-and-Streams-Exercises-Resources\\input.txt"));
            String line = reader.readLine();
            long sum = 0;
            while (line != null){
                for (int i = 0; i < line.length(); i++) {
                    sum += line.charAt(i);
                }
                line = reader.readLine();
            }
            System.out.println(sum);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
