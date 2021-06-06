package T04_StreamsFilesDirectories.Exercise;

import java.io.File;

public class GetFolderSize {
    public static void main(String[] args) {

        try {
            File folder = new File("C:\\Users\\STGRR\\Desktop\\UNIVERSITY\\3. Java Advanced\\New folder\\08. Streams Files Dimensions FunctionalProgramming.DefiningClasses.DefiningClasses.Generics.Lab.Generics.Lab.Generics.Exercise\\04. Java-Advanced-Files-and-Streams-Exercises-Resources\\Exercises Resources");
            File[] files = folder.listFiles();
            long totalBytes = 0;

            if (files != null) {
                for (File file : files) {
                    totalBytes += file.length();
                }
            }
            System.out.printf("Folder size: %d", totalBytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
