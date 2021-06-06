package T04_StreamsFilesDirectories.Lab;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class WriteToFile {
    public static void main(String[] args) {

        String inputPath = "C:\\Users\\STGRR\\Desktop\\UNIVERSITY\\3. Java Advanced\\New folder" +
                "\\07. Streams Files Dimensions\\04. Java-Advanced-Files-and-Streams-DirectoriesFiles.DirectoriesFiles.DirectoriesFiles.FunctionalProgramming.DefiningClasses.DefiningClasses.Generics.Lab.Generics.Lab.DirectoriesFiles.DirectoriesFiles.FunctionalProgramming.DefiningClasses.DefiningClasses.Generics.Lab.Generics.Lab.FunctionalProgramming.DefiningClasses.DefiningClasses.Generics.Lab.Generics.Lab.Generics.Exercise.DirectoriesFiles.FunctionalProgramming.DefiningClasses.DefiningClasses.Generics.Lab.Generics.Lab-Resources" +
                "\\input.txt";
        String outputPath = "C:\\Users\\STGRR\\Desktop\\UNIVERSITY\\3. Java Advanced\\New folder" +
                "\\07. Streams Files Dimensions\\04. Java-Advanced-Files-and-Streams-DirectoriesFiles.DirectoriesFiles.DirectoriesFiles.FunctionalProgramming.DefiningClasses.DefiningClasses.Generics.Lab.Generics.Lab.DirectoriesFiles.DirectoriesFiles.FunctionalProgramming.DefiningClasses.DefiningClasses.Generics.Lab.Generics.Lab.FunctionalProgramming.DefiningClasses.DefiningClasses.Generics.Lab.Generics.Lab.Generics.Exercise.DirectoriesFiles.FunctionalProgramming.DefiningClasses.DefiningClasses.Generics.Lab.Generics.Lab-Resources" +
                "\\output.txt";

        List<Character> symbolsToSkip = new ArrayList<>(Set.of('.', ',', '!', '?'));

        try (InputStream in = new FileInputStream(inputPath);
             OutputStream out = new FileOutputStream(outputPath)) {

            int oneByte = 0;
            while (oneByte >= 0) {
                if (!symbolsToSkip.contains((char) oneByte)) {
                    out.write(oneByte);
                }
                oneByte = in.read();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
