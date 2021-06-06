package T04_StreamsFilesDirectories.Lab;

import java.io.FileInputStream;
import java.io.IOException;

public class ReadFile {
    public static void main(String[] args) {

        String input =
                "C:\\Users\\STGRR\\Desktop\\UNIVERSITY\\3. Java Advanced\\New folder" +
                        "\\07. Streams Files Dimensions\\04. Java-Advanced-Files-and-Streams-DirectoriesFiles.DirectoriesFiles.DirectoriesFiles.FunctionalProgramming.DefiningClasses.DefiningClasses.Generics.Lab.Generics.Lab.DirectoriesFiles.DirectoriesFiles.FunctionalProgramming.DefiningClasses.DefiningClasses.Generics.Lab.Generics.Lab.FunctionalProgramming.DefiningClasses.DefiningClasses.Generics.Lab.Generics.Lab.Generics.Exercise.DirectoriesFiles.FunctionalProgramming.DefiningClasses.DefiningClasses.Generics.Lab.Generics.Lab-Resources" +
                        "\\input.txt";

        try (FileInputStream text = new FileInputStream(input)){
            int oneByte = text.read();
            while (oneByte >= 0){
                System.out.printf("%s ", Integer.toBinaryString(oneByte));
                oneByte = text.read();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
