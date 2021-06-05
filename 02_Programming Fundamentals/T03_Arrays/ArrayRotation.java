package ArraysExercise;

import java.util.Scanner;

public class ArrayRotation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] numInString = scanner.nextLine().split(" ");
        int rotation = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < rotation % numInString.length; i++) { // Този цикъл е оптимизация на код, понеже цикъл от 4 елемента като се завърти 4 пъти си остава същия и изобщо няма смисъл да се върти! Така е и с цикъл от 4 елемента завъртян 400 пъти! Ще въртим 400 пъти, за да получим един и същ резултат ли?
            String temp = numInString[0]; // Първия елемент от масива си го запазваме в променлива, понеже когато го хванем да работим в него ще го заменим с друг и той ще изчезне
            for (int j = 0; j < numInString.length - 1; j++) { // Завъртаме си for цикъл, понеже искаме всеки един от елементите на масива да го изместим наляво ! Слагаме length - 1, защото искаме да си запазим място накрая за първия елемент от масива, който сме запазили във временна променлива.
                numInString[j] = numInString[j + 1]; // Тук казваме, че елемент номер 1(при първа итерация на цикъла) искаме да стане на елемент номер 2 ! И всеки следващ по същия начин, докато не обходим масива до предпоследния елемент
            }
            numInString[numInString.length - 1] = temp; // Тук просто си поставяме първата променлива на последно място.
        }
        System.out.println(String.join(" ", numInString));
    }
}