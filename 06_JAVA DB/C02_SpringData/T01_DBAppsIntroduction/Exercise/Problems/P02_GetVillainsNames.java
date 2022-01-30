package T01_DBAppsIntroduction.Exercise.Problems;

import T01_DBAppsIntroduction.Exercise.Helpers.DatabaseTool;
import java.sql.ResultSet;
import java.sql.SQLException;

public class P02_GetVillainsNames extends BaseProblem {
    public P02_GetVillainsNames(DatabaseTool dbTool) {
        super(dbTool);
    }

    @Override
    public void solve() throws SQLException {
        ResultSet resultSet = dbTool.executeStatementQuery(
                """
                        SELECT `name`, COUNT(DISTINCT minion_id) AS 'minions_count'
                        FROM villains AS v
                        JOIN minions_villains mv on v.id = mv.villain_id
                        GROUP BY mv.villain_id
                        HAVING minions_count > 15
                        ORDER BY minions_count DESC;""");
        while (resultSet.next()) {
            System.out.printf("%s %s%n", resultSet.getString(1), resultSet.getString(2));
        }
    }
}
