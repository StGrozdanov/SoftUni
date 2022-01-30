package T01_DBAppsIntroduction.Exercise.Problems;

import T01_DBAppsIntroduction.Exercise.Helpers.DatabaseTool;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class P03_GetMinionNames extends BaseProblem {
    public P03_GetMinionNames(DatabaseTool dbTool) {
        super(dbTool);
    }

    @Override
    public void solve() throws SQLException {
        System.out.println("Insert the id (integer) of the villain to see information about his minions.");
        int villainId = Integer.parseInt(super.scanner.nextLine());

        PreparedStatement preparedStatement = super.dbTool.preparePreparedStatementQuery(
                """
                        SELECT v.`name`, m.`name`, m.`age`
                        FROM minions AS m
                        JOIN minions_villains AS mv
                        ON m.id = mv.minion_id
                        JOIN villains AS v
                        ON v.id = mv.villain_id
                        WHERE mv.villain_id = ?;""");

        preparedStatement.setInt(1, villainId);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            int counter = 1;

            System.out.printf("Villain: %s%n%d. %s %s%n", resultSet.getString("v.name"), counter,
                    resultSet.getString("m.name"), resultSet.getString("m.age"));

            while (resultSet.next()) {
                counter++;
                System.out.printf("%d. %s %s%n", counter, resultSet.getString("m.name"),
                        resultSet.getString("m.age"));
            }
        } else {
            System.out.printf("No villain with ID %d exists in the database.%n", villainId);
        }
    }
}
