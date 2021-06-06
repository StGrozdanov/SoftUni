package T04_StreamsFilesDirectories.Lab;

import java.io.File;

public class ListFiles {
    public static void main(String[] args) {

        File file = new File("C:\\Users\\STGRR\\Desktop\\UNIVERSITY\\3. Java Advanced\\New folder\\07. Streams Files Dimensions\\04. Java-Advanced-Files-and-Streams-DirectoriesFiles.DirectoriesFiles.DirectoriesFiles.FunctionalProgramming.DefiningClasses.DefiningClasses.Generics.Lab.Generics.Lab.DirectoriesFiles.DirectoriesFiles.FunctionalProgramming.DefiningClasses.DefiningClasses.Generics.Lab.Generics.Lab.FunctionalProgramming.DefiningClasses.DefiningClasses.Generics.Lab.Generics.Lab.Generics.Exercise.DirectoriesFiles.FunctionalProgramming.DefiningClasses.DefiningClasses.Generics.Lab.Generics.Lab-Resources\\Files-and-Streams");

                File[] files = file.listFiles();
                for (File innerFiles : files) {
                    if (!innerFiles.isDirectory()){
                        System.out.printf("%s: [%s]%n", innerFiles.getName(), innerFiles.length());
            }
        }

    }
}
