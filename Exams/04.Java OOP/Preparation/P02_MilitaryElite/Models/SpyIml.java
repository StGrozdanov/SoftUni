package ExamPreparation.P02_MilitaryElite.Models;

public class SpyIml extends SoldierImpl {

    private String codeNumber;

    public SpyIml(int id, String firstName, String lastName, String codeNumber) {
        super(id, firstName, lastName);
        this.codeNumber = codeNumber;
    }

    @Override
    public String toString() {
        return String.format("Name: %s %s Id: %d", getFirstName(), getLastName(), getId()) + System.lineSeparator()
                + String.format("Code Number: %s", this.codeNumber);
    }
}
