package T04_StreamsFilesDirectories.Exercise;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;

public class AllCapitals {
    public static void main(String[] args) {

        try {
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\STGRR\\Desktop\\UNIVERSITY\\3. Java Advanced\\New folder\\08. Streams Files Dimensions FunctionalProgramming.DefiningClasses.DefiningClasses.Generics.Lab.Generics.Lab.Generics.Exercise\\04. Java-Advanced-Files-and-Streams-Exercises-Resources\\input.txt"));
            PrintWriter output = new PrintWriter("C:\\Users\\STGRR\\Desktop\\UNIVERSITY\\3. Java Advanced\\New folder\\08. Streams Files Dimensions FunctionalProgramming.DefiningClasses.DefiningClasses.Generics.Lab.Generics.Lab.Generics.Exercise\\04. Java-Advanced-Files-and-Streams-Exercises-Resources\\output.txt");
            String line = reader.readLine();

            while (line != null){
                output.println(line.toUpperCase());
                line = reader.readLine();
            }
            output.close();
            reader.close();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
