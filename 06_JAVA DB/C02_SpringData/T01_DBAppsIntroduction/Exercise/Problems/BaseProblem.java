package T01_DBAppsIntroduction.Exercise.Problems;

import T01_DBAppsIntroduction.Exercise.Helpers.DatabaseTool;
import T01_DBAppsIntroduction.Exercise.Interfaces.Problem;

import java.util.Scanner;

public abstract class BaseProblem implements Problem {
    protected DatabaseTool dbTool;
    protected Scanner scanner;

    protected BaseProblem(DatabaseTool dbTool) {
        this.dbTool = dbTool;
        this.scanner = new Scanner(System.in);
    }
}
