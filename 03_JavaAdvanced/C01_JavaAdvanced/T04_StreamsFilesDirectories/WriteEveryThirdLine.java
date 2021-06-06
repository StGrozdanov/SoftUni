package T04_StreamsFilesDirectories.Lab;

import java.io.*;

public class WriteEveryThirdLine {
    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\STGRR\\Desktop\\UNIVERSITY" +
                "\\3. Java Advanced\\New folder\\07. Streams Files Dimensions" +
                "\\04. Java-Advanced-Files-and-Streams-DirectoriesFiles.DirectoriesFiles.DirectoriesFiles.FunctionalProgramming.DefiningClasses.DefiningClasses.Generics.Lab.Generics.Lab.DirectoriesFiles.DirectoriesFiles.FunctionalProgramming.DefiningClasses.DefiningClasses.Generics.Lab.Generics.Lab.FunctionalProgramming.DefiningClasses.DefiningClasses.Generics.Lab.Generics.Lab.Generics.Exercise.DirectoriesFiles.FunctionalProgramming.DefiningClasses.DefiningClasses.Generics.Lab.Generics.Lab-Resources" +
                "\\input.txt"));

        PrintWriter out = new PrintWriter("C:\\Users\\STGRR\\Desktop\\UNIVERSITY" +
                "\\3. Java Advanced\\New folder" +
                "\\07. Streams Files Dimensions\\04. Java-Advanced-Files-and-Streams-DirectoriesFiles.DirectoriesFiles.DirectoriesFiles.FunctionalProgramming.DefiningClasses.DefiningClasses.Generics.Lab.Generics.Lab.DirectoriesFiles.DirectoriesFiles.FunctionalProgramming.DefiningClasses.DefiningClasses.Generics.Lab.Generics.Lab.FunctionalProgramming.DefiningClasses.DefiningClasses.Generics.Lab.Generics.Lab.Generics.Exercise.DirectoriesFiles.FunctionalProgramming.DefiningClasses.DefiningClasses.Generics.Lab.Generics.Lab-Resources" +
                "\\output.txt");

        int counter = 1;
        String line = in.readLine();

        while (line != null){
            if (counter % 3 == 0){
                out.println(line);
            }
            counter++;
            line = in.readLine();
        }
        in.close();
        out.close();
    }
}
