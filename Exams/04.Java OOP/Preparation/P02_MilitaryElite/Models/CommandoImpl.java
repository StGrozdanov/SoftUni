package ExamPreparation.P02_MilitaryElite.Models;

import ExamPreparation.P02_MilitaryElite.Objects.Mission;

import java.util.LinkedHashSet;
import java.util.Set;

public class CommandoImpl extends SpecialisedSoldierImpl{

    private Set<Mission> missions;

    public CommandoImpl(int id, String firstName, String lastName, double salary, String corp) {
        super(id, firstName, lastName, salary, corp);
        this.missions = new LinkedHashSet<>();
    }

    public void addMission(Mission mission){
        missions.add(mission);
    }

    public Set<Mission> getMissions() {
        return missions;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Name: %s %s Id: %d Salary: %.2f",
                getFirstName(), getLastName(), getId(), getSalary()))
                .append(System.lineSeparator())
                .append(String.format("Corps: %s", getCorps()))
                .append(System.lineSeparator())
                .append("Missions:")
                .append(System.lineSeparator());
        missions.forEach(r -> {
            builder.append(r)
                    .append(System.lineSeparator());
        });
        return builder.toString().trim();
    }

}
