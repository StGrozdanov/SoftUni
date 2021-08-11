package ExamPreparation.P02_MilitaryElite.Models;

import ExamPreparation.P02_MilitaryElite.Interfaces.Private;
import ExamPreparation.P02_MilitaryElite.Interfaces.Soldier;

import java.util.LinkedHashSet;
import java.util.Set;

public class LieutenantGeneralImpl extends PrivateImpl implements Soldier, Private {

    private Set<PrivateImpl> privates;

    public LieutenantGeneralImpl(int id, String firstName, String lastName, double salary) {
        super(id, firstName, lastName, salary);
        this.privates = new LinkedHashSet<>();
    }
    public void addPrivate (PrivateImpl priv){
        privates.add(priv);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Name: %s %s Id: %d Salary: %.2f",
                getFirstName(), getLastName(), getId(), getSalary()))
                .append(System.lineSeparator())
                .append("Privates:")
                .append(System.lineSeparator());
        privates.stream().sorted((p1, p2) -> Integer.compare(p2.getId(), p1.getId()))
                .forEach(p -> {
                    builder.append(p)
                            .append(System.lineSeparator());
                });
        return builder.toString().trim();
    }
}
