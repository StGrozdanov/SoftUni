package T01_DBAppsIntroduction.Exercise.Problems;

import T01_DBAppsIntroduction.Exercise.Helpers.DatabaseTool;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class P09_IncreaseAgeStoredProcedure extends BaseProblem {
    public P09_IncreaseAgeStoredProcedure(DatabaseTool dbTool) {
        super(dbTool);
    }

    @Override
    public void solve() throws SQLException {
        System.out.println("Select minion ID that gets older");

        int minionId = Integer.parseInt(super.scanner.nextLine());

        CallableStatement callableStatement = dbTool.prepareCall("CALL usp_get_older(?)");
        callableStatement.setInt(1, minionId);
        callableStatement.executeUpdate();

        ResultSet resultSet = dbTool.executeStatementQuery("SELECT `name`, `age` FROM `minions` WHERE `id` = " + minionId);

        if (resultSet.next()) {
            System.out.printf("%s %s%n", resultSet.getString(1), resultSet.getString(2));
        }
    }
}
