package ExamPreparation.P02_MilitaryElite.Models;

import ExamPreparation.P02_MilitaryElite.Interfaces.Private;
import ExamPreparation.P02_MilitaryElite.Interfaces.Soldier;

public class PrivateImpl extends SoldierImpl implements Soldier, Private {

    private double salary;

    public PrivateImpl(int id, String firstName, String lastName, double salary) {
        super(id, firstName, lastName);
        this.salary = salary;
    }

    @Override
    public double getSalary() {
        return salary;
    }

    @Override
    public void addPrivate(Private priv) {
    }
    @Override
    public int getId(){
        return super.getId();
    }
    @Override
    public String toString() {
        return String.format("Name: %s %s Id: %d Salary: %.2f",
                getFirstName(), getLastName(), getId(), getSalary());
    }
}
