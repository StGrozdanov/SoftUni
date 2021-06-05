import java.util.Scanner;

public class LadyBug {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int sizeOfTheField = Integer.parseInt(scanner.nextLine());
        String[] indexes = scanner.nextLine().split(" ");
        int[] field = new int[sizeOfTheField];

        for (int i = 0; i < indexes.length; i++) { // indexes ми играе ролята на i в цикъла, казва на коя позиция в field да
            //си сложа единичка, да ама със стринг няма как да стане, за това ползваме помощна променлива, която ще ни вземе
            // всяка една цифра от стринговия масив и ако се побира в дължината на полето и не е по-малка от 0 ще я запишем
            // като единичка
            int index = Integer.parseInt(indexes[i]);
            if (index >= 0 && index <= sizeOfTheField - 1) {
                field[index] = 1;
            }
        }

        String command = scanner.nextLine();

        while (!"end".equals(command)) {

            String[] commandArgs = command.split(" ");
            int initialField = Integer.parseInt(commandArgs[0]);
            String direction = commandArgs[1];
            int length = Integer.parseInt(commandArgs[2]);

            if (initialField < 0 || initialField > sizeOfTheField - 1 || field[initialField] == 0) {
                command = scanner.nextLine();
                continue;
            } //проверяваме дали изобщо ще лети, защото при определени условия се случва нищо, примерно ако излезне от полето
            // няма да лети, ако индекса, който трябва да лети е 0, то тогава нямаме буболечка и пак няма да лети и ако индекса
            // който трябва да литне е извън полето /отрицателен/ пак няма да лети

            field[initialField] = 0; // ако сме стигнали до тук, нямаме continue и буболечката ще лети, но литне ли индекса на
            //полето трябва да е 0, защото там вече няма буболечка

            if ("right".equals(direction)) {
                initialField += length;

                while (initialField <= sizeOfTheField - 1 && field[initialField] == 1) { //до кога ще лети тая буболечка?
                    initialField += length;
                }
                if (initialField <= sizeOfTheField - 1 && field[initialField] == 0) { //в какъв случай ще кацне в полето?
                    field[initialField] = 1;
                }
            } else {
                initialField -= length;

                while (initialField >= 0 && field[initialField] == 1) {
                    initialField -= length;
                }
                if (initialField >= 0) {
                    field[initialField] = 1;
                }
            }
            command = scanner.nextLine();
        }
        for (int i = 0; i < field.length; i++) {
            System.out.print(field[i] + " ");
        }
        System.out.println();
    }
}
