package T08_IteratorsAndComparators.Lab;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Book implements Comparable<Book>{
    private String title;
    private int year;
    private List<String> authors;

    public Book(String title, int year, String... authors) {
        this.setTitle(title);
        this.setYear(year);
        this.setAuthors(authors);
    }

    private void setTitle(String title) {
        this.title = title;
    }

    private void setYear(int year) {
        this.year = year;
    }

    private void setAuthors(String... authors) {
        if (authors.length == 0){
            this.authors = new ArrayList<>();
        } else {
            this.authors = new ArrayList<>(Arrays.asList(authors));
        }
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public List<String> getAuthors() {
        return authors;
    }



    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (String author : authors) {
            builder.append(author).append(" ");
        }
        return String.format("%s, %d, %s", this.title, this.year, builder.toString().trim());
    }


    @Override
    public int compareTo(Book book) {
        if (this.getTitle().compareTo(book.getTitle()) == 0){
            if (this.getYear() > book.getYear()){
                return 1;
            } else if (this.getYear() < book.getYear()){
                return -1;
            } else {
                return 0;
            }
        }
        return this.getTitle().compareTo(book.getTitle());
    }
}
