package T04_StreamsFilesDirectories.Exercise;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MergeTwoFiles {
    public static void main(String[] args) {

        try {
            BufferedReader file1 = new BufferedReader(new FileReader("C:\\Users\\STGRR\\Desktop\\UNIVERSITY\\3. Java Advanced\\New folder\\08. Streams Files Dimensions FunctionalProgramming.DefiningClasses.DefiningClasses.Generics.Lab.Generics.Lab.Generics.Exercise\\04. Java-Advanced-Files-and-Streams-Exercises-Resources\\inputOne.txt"));
            BufferedReader file2 = new BufferedReader(new FileReader("C:\\Users\\STGRR\\Desktop\\UNIVERSITY\\3. Java Advanced\\New folder\\08. Streams Files Dimensions FunctionalProgramming.DefiningClasses.DefiningClasses.Generics.Lab.Generics.Lab.Generics.Exercise\\04. Java-Advanced-Files-and-Streams-Exercises-Resources\\inputTwo.txt"));

            String firstLine = file1.readLine();
            String secondLine = file2.readLine();

            extractFileInput(file1, firstLine);
            extractFileInput(file2, secondLine);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    private static void extractFileInput(BufferedReader file1, String firstLine) throws IOException {
        while (firstLine != null){
            System.out.println(firstLine);
            firstLine = file1.readLine();
        }
    }
}
