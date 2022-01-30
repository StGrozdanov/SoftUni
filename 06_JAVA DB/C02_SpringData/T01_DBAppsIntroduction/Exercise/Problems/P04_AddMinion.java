package T01_DBAppsIntroduction.Exercise.Problems;

import T01_DBAppsIntroduction.Exercise.Helpers.DatabaseTool;

import java.sql.ResultSet;
import java.sql.SQLException;

public class P04_AddMinion extends BaseProblem {
    public P04_AddMinion(DatabaseTool dbTool) {
        super(dbTool);
    }

    @Override
    public void solve() throws SQLException {
        System.out.println("""
                Please enter valid input of type:
                Minion: minionName minionAge minionTown
                Villain: villainName""");

        String[] minionTokens = super.scanner.nextLine().split(" ");
        String minionName = minionTokens[1];
        String minionAge = minionTokens[2];
        String minionCity = minionTokens[3];
        String villainName = super.scanner.nextLine().split(" ")[1];

        ResultSet targetTown = dbTool.findRecordNameInDB("towns", minionCity);
        ResultSet targetVillain = dbTool.findRecordNameInDB("villains", villainName);

        if (!targetTown.next()) {
            dbTool.insertRecordInDB("towns", "name", minionCity);
            System.out.printf("Town %s was added to the database.%n", minionCity);
        }

        if (!targetVillain.next()) {
            String defaultEvilness = "evil";
            dbTool.insertRecordInDB("villains", "name", "evilness_factor", villainName, defaultEvilness);
            System.out.printf("Villain %s was added to the database.%n", villainName);
        }

        String featuredTownId = dbTool.findRecordIdByNameInDB("towns", minionCity);
        dbTool.insertRecordInDB("minions", "name", "age", "town_id", minionName, minionAge, featuredTownId);

        String featuredVillainId = dbTool.findRecordIdByNameInDB("villains", villainName);
        String featuredMinionId = dbTool.findRecordIdByNameInDB("minions", minionName);

        dbTool.insertRecordInDB("minions_villains", "minion_id", "villain_id", featuredMinionId, featuredVillainId);
        System.out.printf("Successfully added %s to be minion of %s%n", minionName, villainName);
    }
}
