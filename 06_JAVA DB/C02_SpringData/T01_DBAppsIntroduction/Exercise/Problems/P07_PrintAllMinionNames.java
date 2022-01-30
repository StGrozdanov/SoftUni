package T01_DBAppsIntroduction.Exercise.Problems;

import T01_DBAppsIntroduction.Exercise.Helpers.DatabaseTool;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class P07_PrintAllMinionNames extends BaseProblem{
    public P07_PrintAllMinionNames(DatabaseTool dbTool) {
        super(dbTool);
    }

    @Override
    public void solve() throws SQLException {
        ResultSet resultSet = dbTool.executeStatementQuery("SELECT `name` FROM `minions`;");
        List<String> minionNames = new ArrayList<>();

        while (resultSet.next()) {
            minionNames.add(resultSet.getString(1));
        }

        int startIndex = 0;
        int endIndex = minionNames.size() - 1;

        for (int i = 0; i < minionNames.size(); i++) {
            System.out.println(i % 2 == 0
                    ? minionNames.get(startIndex++)
                    : minionNames.get(endIndex--));
        }

        //Update statements in previous tasks /P04_AddMinion/ break the inspected output from the document, work with clean DB
        //If u want to compare with document output
    }
}
