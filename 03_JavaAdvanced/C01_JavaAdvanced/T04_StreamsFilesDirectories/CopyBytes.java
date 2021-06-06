package T04_StreamsFilesDirectories.Lab;

import java.io.*;

public class CopyBytes {
    public static void main(String[] args) {

        String inputPath = "C:\\Users\\STGRR\\Desktop\\UNIVERSITY\\3. Java Advanced\\New folder" +
                "\\07. Streams Files Dimensions\\04. Java-Advanced-Files-and-Streams-DirectoriesFiles.DirectoriesFiles.DirectoriesFiles.FunctionalProgramming.DefiningClasses.DefiningClasses.Generics.Lab.Generics.Lab.DirectoriesFiles.DirectoriesFiles.FunctionalProgramming.DefiningClasses.DefiningClasses.Generics.Lab.Generics.Lab.FunctionalProgramming.DefiningClasses.DefiningClasses.Generics.Lab.Generics.Lab.Generics.Exercise.DirectoriesFiles.FunctionalProgramming.DefiningClasses.DefiningClasses.Generics.Lab.Generics.Lab-Resources" +
                "\\input.txt";
        String outputPath = "C:\\Users\\STGRR\\Desktop\\UNIVERSITY\\3. Java Advanced\\New folder" +
                "\\07. Streams Files Dimensions\\04. Java-Advanced-Files-and-Streams-DirectoriesFiles.DirectoriesFiles.DirectoriesFiles.FunctionalProgramming.DefiningClasses.DefiningClasses.Generics.Lab.Generics.Lab.DirectoriesFiles.DirectoriesFiles.FunctionalProgramming.DefiningClasses.DefiningClasses.Generics.Lab.Generics.Lab.FunctionalProgramming.DefiningClasses.DefiningClasses.Generics.Lab.Generics.Lab.Generics.Exercise.DirectoriesFiles.FunctionalProgramming.DefiningClasses.DefiningClasses.Generics.Lab.Generics.Lab-Resources" +
                "\\output.txt";

        try (InputStream in = new FileInputStream(inputPath);
             OutputStream out = new FileOutputStream(outputPath)) {

            int oneByte = 0;
            while (oneByte >= 0) {
                String ascii = String.valueOf(oneByte);
                if (!ascii.equals(String.valueOf(10)) && !ascii.equals(String.valueOf(32))) {
                    for (int i = 0; i < ascii.length(); i++) {
                        out.write(ascii.charAt(i));
                    }
                } else {
                    out.write(oneByte);
                }
                oneByte = in.read();
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
