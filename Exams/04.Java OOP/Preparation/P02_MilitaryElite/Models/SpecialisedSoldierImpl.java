package ExamPreparation.P02_MilitaryElite.Models;

import ExamPreparation.P02_MilitaryElite.Enums.Corps;
import ExamPreparation.P02_MilitaryElite.Interfaces.Corp;
import ExamPreparation.P02_MilitaryElite.Interfaces.Private;

import java.util.Arrays;

public class SpecialisedSoldierImpl extends PrivateImpl implements Corp {

    private String corps;

    public SpecialisedSoldierImpl(int id, String firstName, String lastName, double salary, String corp) {
        super(id, firstName, lastName, salary);
        setCorps(corp);
    }

    @Override
    public void setCorps(String corp) {
        if (Arrays.stream(Corps.values()).anyMatch(c -> c.name().equals(corp.toUpperCase()))) {
            this.corps = corp;
        } else {
            throw new IllegalStateException("Corps not valid");
        }
    }

    @Override
    public String getCorps() {
        return corps;
    }

    @Override
    public void addPrivate(Private priv) {

    }
}
