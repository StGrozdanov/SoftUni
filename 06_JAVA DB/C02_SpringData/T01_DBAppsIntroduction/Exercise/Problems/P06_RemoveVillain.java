package T01_DBAppsIntroduction.Exercise.Problems;

import T01_DBAppsIntroduction.Exercise.Helpers.DatabaseTool;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class P06_RemoveVillain extends BaseProblem{
    public P06_RemoveVillain(DatabaseTool dbTool) {
        super(dbTool);
    }

    @Override
    public void solve() throws SQLException {
        System.out.println("Insert the ID of the villain you want to delete from the Database");
        int villainId = Integer.parseInt(super.scanner.nextLine());

        ResultSet resultSet = dbTool.findRecordNameInDBByID("villains", villainId);

        if (resultSet.next()) {
            PreparedStatement targetMinionsStatement = dbTool
                    .preparePreparedStatementQuery("DELETE FROM `minions_villains` WHERE `villain_id` = ?");
            targetMinionsStatement.setInt(1, villainId);
            int deletedMinionsCount = targetMinionsStatement.executeUpdate();

            PreparedStatement targetVillainStatement = dbTool
                    .preparePreparedStatementQuery("DELETE FROM `villains` WHERE `id` = ?");
            targetVillainStatement.setInt(1, villainId);
            targetVillainStatement.executeUpdate();

            System.out.printf("%s was deleted%n%d minions released%n", resultSet.getString(1), deletedMinionsCount);
        } else {
            System.out.println("No such villain was found");
        }
    }
}
