package ExamPreparation.P02_MilitaryElite.Models;

import ExamPreparation.P02_MilitaryElite.Objects.Repair;

import java.util.LinkedHashSet;
import java.util.Set;

public class EngineerImpl extends SpecialisedSoldierImpl {

    private Set<Repair> repairs;

    public EngineerImpl(int id, String firstName, String lastName, double salary, String corp) {
        super(id, firstName, lastName, salary, corp);
        this.repairs = new LinkedHashSet<>();
    }

    public void addRepair(Repair repair) {
        this.repairs.add(repair);
    }

    public Set<Repair> getRepairs() {
        return repairs;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Name: %s %s Id: %d Salary: %.2f",
                getFirstName(), getLastName(), getId(), getSalary()))
                .append(System.lineSeparator())
                .append(String.format("Corps: %s", getCorps()))
                .append(System.lineSeparator())
                .append("Repairs:")
                .append(System.lineSeparator());
        repairs.forEach(r -> {
            builder.append(r)
                    .append(System.lineSeparator());
        });
        return builder.toString().trim();
    }

}
