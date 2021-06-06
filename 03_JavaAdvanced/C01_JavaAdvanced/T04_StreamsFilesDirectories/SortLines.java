package T04_StreamsFilesDirectories.Lab;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SortLines {
    public static void main(String[] args) {

        Path in = Paths.get("C:\\Users\\STGRR\\Desktop\\UNIVERSITY" +
                "\\3. Java Advanced\\New folder\\07. Streams Files Dimensions" +
                "\\04. Java-Advanced-Files-and-Streams-DirectoriesFiles.DirectoriesFiles.DirectoriesFiles.FunctionalProgramming.DefiningClasses.DefiningClasses.Generics.Lab.Generics.Lab.DirectoriesFiles.DirectoriesFiles.FunctionalProgramming.DefiningClasses.DefiningClasses.Generics.Lab.Generics.Lab.FunctionalProgramming.DefiningClasses.DefiningClasses.Generics.Lab.Generics.Lab.Generics.Exercise.DirectoriesFiles.FunctionalProgramming.DefiningClasses.DefiningClasses.Generics.Lab.Generics.Lab-Resources" +
                "\\input.txt");
        Path out = Paths.get("C:\\Users\\STGRR\\Desktop\\UNIVERSITY" +
                "\\3. Java Advanced\\New folder" +
                "\\07. Streams Files Dimensions\\04. Java-Advanced-Files-and-Streams-DirectoriesFiles.DirectoriesFiles.DirectoriesFiles.FunctionalProgramming.DefiningClasses.DefiningClasses.Generics.Lab.Generics.Lab.DirectoriesFiles.DirectoriesFiles.FunctionalProgramming.DefiningClasses.DefiningClasses.Generics.Lab.Generics.Lab.FunctionalProgramming.DefiningClasses.DefiningClasses.Generics.Lab.Generics.Lab.Generics.Exercise.DirectoriesFiles.FunctionalProgramming.DefiningClasses.DefiningClasses.Generics.Lab.Generics.Lab-Resources" +
                "\\output.txt");

        try {
            List<String> lines = Files.readAllLines(in);
            lines = lines.stream().filter(i -> !i.isBlank()).collect(Collectors.toList());
            Collections.sort(lines);
            Files.write(out, lines);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
