package ObjectsAndClassesExercise10.Articles2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        List<Articles> collection = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String[] inputArticle = scanner.nextLine().split(", ");
            String title = inputArticle[0];
            String content = inputArticle[1];
            String author = inputArticle[2];
            Articles articles = new Articles(title, content, author);
            collection.add(articles);
        }

        String command = scanner.nextLine();

        switch (command) {
            case "title":
                collection.stream().sorted((c1, c2) -> c1.getTitle().compareTo(c2.getTitle())).
                        forEach(c -> System.out.printf("%s - %s: %s%n", c.getTitle(), c.getContent(), c.getAuthor()));
                break;
            case "content":
                collection.stream().sorted((c1, c2) -> c1.getContent().compareTo(c2.getContent())).
                        forEach(c -> System.out.printf("%s - %s: %s%n", c.getTitle(), c.getContent(), c.getAuthor()));
                break;
            case "author":
                collection.stream().sorted((c1, c2) -> c1.getAuthor().compareTo(c2.getAuthor())).
                        forEach(c -> System.out.printf("%s - %s: %s%n", c.getTitle(), c.getContent(), c.getAuthor()));
                break;
        }
    }
}
