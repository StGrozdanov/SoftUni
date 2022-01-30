package T01_DBAppsIntroduction.Exercise.Problems;

import T01_DBAppsIntroduction.Exercise.Helpers.DatabaseTool;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class P08_IncreaseMinionsAge extends BaseProblem{
    public P08_IncreaseMinionsAge(DatabaseTool dbTool) {
        super(dbTool);
    }

    @Override
    public void solve() throws SQLException {
        System.out.println("Enter target minion ID's, separated by space");
        String[] tokens = super.scanner.nextLine().split("\s+");

        for (String minionId : tokens) {
            PreparedStatement preparedStatement = dbTool.preparePreparedStatementQuery(
                    """
                            UPDATE `minions`
                            SET `name` = LCASE(`name`), `age` = `age` + 1
                            WHERE `id` = ?;""");
            preparedStatement.setString(1, minionId);
            preparedStatement.executeUpdate();
        }
        ResultSet resultSet = dbTool.executeStatementQuery("SELECT `name`, `age` FROM `minions`;");
        while (resultSet.next()) {
            System.out.printf("%s %s%n", resultSet.getString(1), resultSet.getString(2));
        }
    }
}
