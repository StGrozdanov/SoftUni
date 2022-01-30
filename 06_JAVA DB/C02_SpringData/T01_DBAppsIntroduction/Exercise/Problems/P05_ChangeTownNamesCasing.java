package T01_DBAppsIntroduction.Exercise.Problems;

import T01_DBAppsIntroduction.Exercise.Helpers.DatabaseTool;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class P05_ChangeTownNamesCasing extends BaseProblem{
    public P05_ChangeTownNamesCasing(DatabaseTool dbTool) {
        super(dbTool);
    }

    @Override
    public void solve() throws SQLException {
        System.out.println("Input target country");
        String targetCountry = super.scanner.nextLine();

        PreparedStatement preparedStatement = dbTool.preparePreparedStatementQuery(
                """
                        UPDATE `towns`
                        SET `name` = UPPER(`name`)
                        WHERE `country` = ?;""");

        preparedStatement.setString(1, targetCountry);
        int affectedRows = preparedStatement.executeUpdate();

        if (affectedRows > 0) {
            PreparedStatement affectedTownsStatement = dbTool
                    .preparePreparedStatementQuery("SELECT `name` FROM `towns` WHERE `country` = ?;");

            affectedTownsStatement.setString(1, targetCountry);
            ResultSet resultSet = affectedTownsStatement.executeQuery();

            List<String> affectedTowns = new ArrayList<>();

            while (resultSet.next()) {
                affectedTowns.add(resultSet.getString("name"));
            }

            System.out.printf("%d town names were affected.%n", affectedRows);
            System.out.printf("[%s]%n", String.join(", ", affectedTowns));
        } else {
            System.out.println("No town names were affected.");
        }
    }
}
