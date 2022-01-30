package T01_DBAppsIntroduction.Exercise;

import T01_DBAppsIntroduction.Exercise.Helpers.DatabaseTool;
import T01_DBAppsIntroduction.Exercise.Interfaces.Problem;
import T01_DBAppsIntroduction.Exercise.Problems.*;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        Properties props = getProperties(scanner);
        DatabaseTool dbTool = new DatabaseTool(props);
        programController(scanner, dbTool);
    }

    private static void programController(Scanner scanner, DatabaseTool dbTool) {
        System.out.println("Please, write down the exercise number you want to test - from 2 to 9");

        String input = scanner.nextLine();

        while (!input.equals("finish")){
            int exerciseNumber = Integer.parseInt(input);
            testExerciseProblems(exerciseNumber, dbTool);
            System.out.println("Please enter the next exercise number you want to test or the same exercise number " +
                    "as the previous if you want to test the exercise with different input or write finish to end the " +
                    "program");
            input = scanner.nextLine();
        }
    }

    private static void testExerciseProblems(int problemNumber, DatabaseTool dbTool) {
        Problem P02_GetVillainsNames = new P02_GetVillainsNames(dbTool);
        Problem P03_GetMinionNames = new P03_GetMinionNames(dbTool);
        Problem P04_AddMinion = new P04_AddMinion(dbTool);
        Problem P05_ChangeTownNamesCasing = new P05_ChangeTownNamesCasing(dbTool);
        Problem P06_RemoveVillain = new P06_RemoveVillain(dbTool);
        Problem P07_PrintAllMinionNames = new P07_PrintAllMinionNames(dbTool);
        Problem P08_IncreaseMinionsAge = new P08_IncreaseMinionsAge(dbTool);
        Problem P09_IncreaseAgeStoredProcedure = new P09_IncreaseAgeStoredProcedure(dbTool);

        Map<Integer, Problem> problemMap = new LinkedHashMap<>(Map.of(
                2, P02_GetVillainsNames,
                3, P03_GetMinionNames,
                4, P04_AddMinion,
                5, P05_ChangeTownNamesCasing,
                6, P06_RemoveVillain,
                7, P07_PrintAllMinionNames,
                8, P08_IncreaseMinionsAge,
                9, P09_IncreaseAgeStoredProcedure
        ));

        try {
            problemMap.get(problemNumber).solve();
        } catch (Exception e) {
            System.out.println("There isn't such exercise number in my homework :)");
        }
    }

    private static Properties getProperties(Scanner scanner) {
        Properties props = new Properties();

        System.out.printf("Hello, please enter your local database credentials:%nUsername:%n");
        String username = scanner.nextLine();
        System.out.println("Password:");
        String password = scanner.nextLine();

        props.setProperty("user", username);
        props.setProperty("password", password);

        return props;
    }
}
