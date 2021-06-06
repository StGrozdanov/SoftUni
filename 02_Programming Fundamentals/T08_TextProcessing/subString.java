package TextProcessing;

import java.util.Scanner;

public class subString {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String match = scanner.nextLine();
        String text = scanner.nextLine();

        int index = text.indexOf(match);
        while(index != -1){
            text = text.replace(match, "");
            index = text.indexOf(match);
        }
        System.out.println(text);
    }
}
