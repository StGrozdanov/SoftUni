package ObjectsAndClassesExercise10.Articles;

import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] inputArticle = scanner.nextLine().split(", ");
        int n = Integer.parseInt(scanner.nextLine());

        Articles articles = new Articles(inputArticle[0], inputArticle[1], inputArticle[2]);

        for (int i = 0; i < n; i++) {

            String[] tokens = scanner.nextLine().split(": ");
            String command = tokens[0];
            String newContent = tokens[1];

            switch (command) {
                case "Edit":
                    articles.edit(newContent);
                    break;
                case "ChangeAuthor":
                    articles.changeAuthor(newContent);
                    break;
                case "Rename":
                    articles.rename(newContent);
                    break;
            }
        }
        System.out.println(articles);
    }
}
