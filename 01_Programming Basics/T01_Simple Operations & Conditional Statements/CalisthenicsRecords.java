import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CalisthenicsRecords {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter exercise type with small letters");
        String exerciseType = scanner.nextLine();

        double dipSum = 0;
        double pullSum = 0;
        double rollSum = 0;
        double pushSum = 0;
        double pikeSum = 0;
        double bicepSum = 0;
        double neckSum = 0;
        double glutSum = 0;
        double reverseSum = 0;
        double pistolSum = 0;
        double squadsSum = 0;
        double bulgarianSum = 0;

        List<Double> currentSession = new ArrayList<>();

        while (!"end".equals(exerciseType)) {
            switch (exerciseType) {
                case "dips":
                    System.out.println("Type 1 for regular dips");
                    System.out.println("Type 2 for baby ring dips");
                    System.out.println("Type 3 for weighted bar dips");
                    System.out.println("Type 4 for regular ring dips");
                    System.out.println("Type 5 for weighted ring dips");
                    System.out.println("Type 6 for bulgarian dips");
                    System.out.println("Type 7 for bulgarian weighted dips");
                    double dipType = Double.parseDouble(scanner.nextLine());
                    dipSum += dipExerciseScore(dipType, scanner);
                    break;
                case "pull ups":
                    System.out.println("Type 1 for baby ring pull ups.");
                    System.out.println("Type 2 for regular pull ups.");
                    System.out.println("Type 3 for ring pull ups.");
                    System.out.println("Type 4 for front leg pull ups");
                    System.out.println("Type 5 for ring front leg pull ups");
                    System.out.println("Type 6 for weighted front leg pull ups");
                    System.out.println("Type 7 for weighted ring front leg pull ups");
                    double pullType = Double.parseDouble(scanner.nextLine());
                    pullSum += pullExerciseScore(pullType, scanner);
                    break;
                case "rolls":
                    System.out.println("Type 1 for regular rolls");
                    System.out.println("Type 2 for ring rolls");
                    System.out.println("Type 3 for archer ring rolls");
                    System.out.println("Type 4 for one handed ring rolls");
                    System.out.println("Type 5 for weighted one handed ring rolls");
                    System.out.println("Type 6 for baby front lever rolls");
                    System.out.println("Type 7 for extended leg front lever rolls");
                    System.out.println("Type 8 for full front lever rolls");
                    double rollType = Double.parseDouble(scanner.nextLine());
                    rollSum += rollExerciseScore(rollType, scanner);
                    break;
                case "push ups":
                    System.out.println("Type 1 for regular push ups");
                    System.out.println("Type 2 for weighted push ups");
                    System.out.println("Type 3 for ring push ups");
                    System.out.println("Type 4 for inverted ring push ups");
                    System.out.println("Type 5 for weighted ring push ups");
                    System.out.println("Type 6 for inverted weighted ring push ups");
                    System.out.println("Type 7 for archer push ups");
                    System.out.println("Type 8 for inverted archer push ups");
                    System.out.println("Type 9 for weighted archer push ups");
                    double pushType = Double.parseDouble(scanner.nextLine());
                    pushSum += pushExerciseScore(pushType, scanner);
                    break;
                case "pike press":
                    System.out.println("Type 1 for regular pike press");
                    System.out.println("Type 2 for elevated pike press");
                    System.out.println("Type 3 for weighted pike press");
                    System.out.println("Type 4 for weighted elevated pike press");
                    double pikeType = Double.parseDouble(scanner.nextLine());
                    pikeSum += pikeExerciseScore(pikeType, scanner);
                    break;
                case "bicep curls":
                    System.out.println("Type 1 for ring bicep curls");
                    System.out.println("Type 2 for weighted bicep curls");
                    System.out.println("Type 3 for one handed bicep curls");
                    System.out.println("Type 4 for weighted one handed bicep curls");
                    double bicepType = Double.parseDouble(scanner.nextLine());
                    bicepSum += bicepExerciseScore(bicepType, scanner);
                    break;
                case "neck":
                    System.out.println("Type 1 for regular neck exercises");
                    System.out.println("Type 2 for side-to-side neck exercises");
                    double neckType = Double.parseDouble(scanner.nextLine());
                    neckSum += neckExerciseScore(scanner, neckType);
                    break;
                case "glut bridge":
                    glutSum += ExerciseScore(scanner);
                    break;
                case "reverse lounge":
                    reverseSum += ExerciseScore(scanner);
                    break;
                case "pistol squads":
                    System.out.println("Type 1 for baby pistol squads");
                    System.out.println("Type 2 for semi-supported half way down pistol squads");
                    System.out.println("Type 3 for half way down pistol squads");
                    System.out.println("Type 4 for full pistol squads");
                    double pistolType = Double.parseDouble(scanner.nextLine());
                    pistolSum += pistolExerciseScore(pistolType, scanner);
                    break;
                case "squads":
                    squadsSum += ExerciseScore(scanner);
                    break;
                case "bulgarian split squad":
                    bulgarianSum += ExerciseScore(scanner);
                    break;
            }
            System.out.println("Enter your next exercise or type end if u are done.");
            exerciseType = scanner.nextLine();
        }
        currentSession.add(0, dipSum);
        currentSession.add(1, pullSum);
        currentSession.add(2, rollSum);
        currentSession.add(3, pushSum);
        currentSession.add(4, pikeSum);
        currentSession.add(5, bicepSum);
        currentSession.add(6, neckSum);
        currentSession.add(7, glutSum);
        currentSession.add(8, reverseSum);
        currentSession.add(9, pistolSum);
        currentSession.add(10, squadsSum);
        currentSession.add(11, bulgarianSum);
        System.out.println(currentSession);

        // Рекорди:
        // Кофички - 326 точки [13.03.2021] 1x11+3x10 bulgarian + 2x10 regular ring
        // Набирания - 132 точки [31.07.2019] 3x11 weighed
        // Rolls - 296 точки [06.03.2021] 4x10 one handed, weighted vest, lvl 4
        // Лицеви - 280 точки [06.03.2021] 4x10 archer ring
        // Pike Press - 250 точки [11.03.2021] 2x10 3 step elevated, 1x10 4 step elevated all weighted vest
        // Bicep Curls - 124.5 точки [11.03.2021] 3x10 lvl 4 weighted vest
        // Врат - 870 точки [11.03.2021] 11 kg 5x10 + 4 kg 2x10
        // Glut Bridge - 840 точки [11.03.2021] 3x10 28 kg
        // Reverse lunge - 360 точки [11.03.2021] 3x10 12 kg
        // Pistol Squads - 40 точки [13.02.2021]
        // Squads - 1200 точки [20.02.2021] 4x10 30 kg weight
        // BG Split squads - 780 точки [20.02.2021] 1x10 30 kg weight + 2x10 24 kg weight

        List<Double> previousSession = new ArrayList<>(Arrays.asList(326.0, 111.0, 296.0, 280.0, 240.0, 72.0, 770.0, 660.0, 300.0, 40.0, 1200.0, 780.0));
        List<Double> records = new ArrayList<>(Arrays.asList(326.0, 132.0, 296.0, 280.0, 250.0, 124.5, 870.0, 840.0, 360.0, 40.0, 1200.0, 780.0));

        //error at lever type rolls - less points & asks for level ... the fuck
        //error at push up exercise - no inverted archer type?

        for (int i = 0; i < currentSession.size(); i++) {
            if (currentSession.get(i) == 0){
                continue;
            }
            if (currentSession.get(i) > previousSession.get(i)){
                System.out.print("Progress at "); exerciseNamesAtPosition(i); System.out.print(" Index " + i);
                System.out.println();
            } else if (currentSession.get(i).equals(previousSession.get(i))){
                System.out.print("Neutral progression at "); exerciseNamesAtPosition(i); System.out.print(" Index " + i);
                System.out.println();
            } else {
                System.out.print("Regression at "); exerciseNamesAtPosition(i); System.out.print(" Index " + i);
                System.out.println();
            }
        }

        for (int i = 0; i < currentSession.size(); i++) {
            if (currentSession.get(i) > records.get(i)){
                System.out.print("NEW RECORD !!! "); exerciseNamesAtPosition(i); System.out.print(" Index " + i);
                System.out.println();
            }
        }
    }







    private static void exerciseNamesAtPosition(int i) {

        switch(i){
            case 0:
                System.out.print("Dips exercise!");
                break;
            case 1:
                System.out.print("Pull Up exercise!");
                break;
            case 2:
                System.out.print("Rolls For Da Hols exercise!");
                break;
            case 3:
                System.out.print("Push Ups exercise!");
                break;
            case 4:
                System.out.print("Pikes exercise!");
                break;
            case 5:
                System.out.print("Bicep Curls exercise!");
                break;
            case 6:
                System.out.print("Neck exercise!");
                break;
            case 7:
                System.out.print("Glut Bridge exercise!");
                break;
            case 8:
                System.out.print("Reverse Squad exercise!");
                break;
            case 9:
                System.out.print("Pistol Squads exercise!");
                break;
            case 10:
                System.out.print("Squads exercise!");
                break;
            case 11:
                System.out.print("Bulgarian Squads exercise!");
                break;
        }
    }

    private static double pistolExerciseScore(double pistolType, Scanner scanner) {
        System.out.println("Enter total repetitions");
        double repetitions = Double.parseDouble(scanner.nextLine());

        return pistolType * repetitions;
    }

    private static double ExerciseScore(Scanner scanner) {
        System.out.println("Please, enter the weight in kg");
        double weight = Double.parseDouble(scanner.nextLine());
        System.out.println("Enter total repetitions");
        double repetitions = Double.parseDouble(scanner.nextLine());

        return weight * repetitions;
    }

    private static double neckExerciseScore(Scanner scanner, double neckType) {
        System.out.println("Please, enter the weight in kg");
        double dipWeight = Double.parseDouble(scanner.nextLine());
        if (neckType == 2){
            dipWeight = dipWeight * 4;
        }
        System.out.println("Enter total repetitions");
        double dipRepetitions = Double.parseDouble(scanner.nextLine());

        return dipWeight * dipRepetitions;
    }

    private static double bicepExerciseScore(double bicepType, Scanner scanner) {
        if (bicepType == 2 || bicepType == 4) {
            System.out.println("Please, enter the weight in kg");
            double weight = Double.parseDouble(scanner.nextLine()) / 10;
            if (bicepType == 2) {
                weight = weight * 0.75;
            }
            bicepType = bicepType + weight;
        }
        System.out.println("Enter level 1-7");
        double level = Double.parseDouble(scanner.nextLine());
        bicepType += level*0.35;
        System.out.println("Enter total repetitions");
        double repetitions = Double.parseDouble(scanner.nextLine());

        return bicepType * repetitions;
    }

    private static double pikeExerciseScore(double pikeType, Scanner scanner) {
        double initialInput = pikeType;
        if (pikeType >= 3) {
            System.out.println("Please, enter the weight in kg");
            double dipWeight = Double.parseDouble(scanner.nextLine()) / 10;
            pikeType = pikeType + dipWeight;
        }
        if (initialInput == 2 || initialInput == 4){
            System.out.println("Enter elevation from 1 to 4 steps.");
            double multiplier = Double.parseDouble(scanner.nextLine());
            pikeType = ((multiplier*0.2)+1)*(pikeType);
        }
        System.out.println("Enter total repetitions");
        double dipRepetitions = Double.parseDouble(scanner.nextLine());

        return pikeType * dipRepetitions;
    }

    private static double pushExerciseScore(double pushType, Scanner scanner) {

        if (pushType == 5 || pushType == 6 || pushType == 9) {
            System.out.println("Please, enter the weight in kg");
            double weight = Double.parseDouble(scanner.nextLine()) / 10;
            pushType = pushType * 2 + weight;
        }
        System.out.println("Enter total repetitions");
        double repetitions = Double.parseDouble(scanner.nextLine());
        if (pushType == 1) {
            repetitions = repetitions * 0.9;
        } else if (pushType == 2) {
            repetitions = repetitions + 1;
            repetitions = repetitions * 0.9;
        }
        return pushType * repetitions;
    }

    private static double rollExerciseScore(double rollType, Scanner scanner) {
        if (rollType == 5) {
            System.out.println("Please, enter the weight in kg");
            double weight = Double.parseDouble(scanner.nextLine()) / 10;
            rollType = rollType + weight;
        }
        System.out.println("Enter level 1-7");
        double level = Double.parseDouble(scanner.nextLine());
        rollType += level*0.35;
        System.out.println("Enter total repetitions");
        double repetitions = Double.parseDouble(scanner.nextLine());

        return rollType * repetitions;
    }

    private static double pullExerciseScore(double pullType, Scanner scanner) {
        if (pullType >= 6) {
            System.out.println("Please, enter the weight in kg");
            double dipWeight = Double.parseDouble(scanner.nextLine()) / 10;
            pullType = pullType + dipWeight;
        }
        System.out.println("Enter total repetitions");
        double dipRepetitions = Double.parseDouble(scanner.nextLine());

        return pullType * dipRepetitions;
    }

    private static double dipExerciseScore(double dipType, Scanner scanner) {
        if (dipType == 3 || dipType == 5 || dipType == 7) {
            System.out.println("Please, enter the weight in kg");
            double dipWeight = Double.parseDouble(scanner.nextLine()) / 10;
            dipType = dipType + dipWeight;
        }
        System.out.println("Enter total repetitions");
        double dipRepetitions = Double.parseDouble(scanner.nextLine());

        return dipType * dipRepetitions;
    }
    private static void hello(Enum e){

    }
}
