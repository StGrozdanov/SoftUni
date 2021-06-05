package Arrays;

import java.util.Scanner;

public class ReverseArrayOfStrings {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] strings = scanner.nextLine().split(" ");

        for (int i = 0; i < strings.length / 2; i++){  // Разделяме дължината на масива на две, защото за да разменим местата на няколко променливи ни трябва средната променлива и ние щем нещем винаги работим около нея!
            String temp = strings[i]; // За да обърнем дадено нещо, винаги трябва първия елемент да застане, като последен и чрез това преравняване хващаме първия елемент от масива за ушите!
            strings[i] = strings[strings.length - 1 - i]; // Това го правим, защото първата позиция на масива винаги е 0, следващата 1, следващата 2, т.е. имаме формула от дължината на масива - 1, НО и -i ! Така хващаме последния елемент от масива за ушите
            strings[strings.length - 1 - i] = temp; // Това ще ни завърти целия масив наобратно, защото преравняваме последния елемент от масива с първия !
        }
        System.out.println(String.join(" ", strings)); // Методът String.join ще ни изпечата масива без for цикъл на 1 ред
    }
}
