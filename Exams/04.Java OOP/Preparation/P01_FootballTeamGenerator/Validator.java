package ExamPreparation.P01_FootballTeamGenerator;

public class Validator {

    public static void statValidator(int stat, String name) {
        if (stat < 0 || stat > 100) {
            throw new IllegalStateException(String.format("%s should be between 0 and 100.%n", name));
        }
    }

    public static void nameValidator(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalStateException("A name should not be empty.");
        }
    }

}
