package ListsExercise08;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class BombNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> list = Arrays.stream(scanner.nextLine().split("\\s+")).map(Integer::parseInt).collect(Collectors.toList());
        String[] input = scanner.nextLine().split("\\s+");

        int bombNumber = Integer.parseInt(input[0]);
        int power = Integer.parseInt(input[1]);

        while(list.contains(bombNumber)){
            int index = list.indexOf(bombNumber); // първо си намираме на кой индекс се намира бомбата
            int leftBound = Math.max(index-power, 0); //определяме си лява граница, казваме index-power, понеже знаем
            // условието, но ако имаме index 2 и сила 4, това ще ни даде индекс -2 и умираме, за това при този вариант
            // 0 ще ни се вземе като индекс при math max
            int rightBound = Math.min(index+power, list.size()-1);

            for (int i = rightBound; i >= leftBound; i--) { // вървим обратно на цикъла, защото ако започнем от лявата
                // граница числата постоянно ще ни се преместват като трием нещо отляво ще ходят на ляво, а ако го правим
                // отдясно наляво това не ни бърка
                list.remove(i);
            }
        }
        int sum = 0;
        for (int sumPrint:list){
            sum += sumPrint;
        }
        System.out.println(sum);
    }
}
