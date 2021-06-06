package T04_StreamsFilesDirectories.Lab;

import java.io.File;
import java.util.ArrayDeque;
import java.util.Deque;

public class NestedFolders {
    public static void main(String[] args) {
        File file = new File("C:\\Users\\STGRR\\Desktop\\UNIVERSITY\\3. Java Advanced\\New folder\\07. Streams Files Dimensions\\04. Java-Advanced-Files-and-Streams-DirectoriesFiles.DirectoriesFiles.DirectoriesFiles.FunctionalProgramming.DefiningClasses.DefiningClasses.Generics.Lab.Generics.Lab.DirectoriesFiles.DirectoriesFiles.FunctionalProgramming.DefiningClasses.DefiningClasses.Generics.Lab.Generics.Lab.FunctionalProgramming.DefiningClasses.DefiningClasses.Generics.Lab.Generics.Lab.Generics.Exercise.DirectoriesFiles.FunctionalProgramming.DefiningClasses.DefiningClasses.Generics.Lab.Generics.Lab-Resources\\Files-and-Streams");

        Deque<File> dirs = new ArrayDeque<>();
        dirs.offer(file);

        int counter = 0;

        while (!dirs.isEmpty()){
            File current = dirs.poll();
            File[] nestedFiles = current.listFiles();
            for (File nestedFile : nestedFiles) {
                if (nestedFile.isDirectory()){
                    dirs.offer(nestedFile);
                }
            }
            counter++;
            System.out.println(current.getName());
        }
        System.out.println(counter + " folders");
    }
}
