package T06_Reflection.Exercise.P03_BarracksWarsANewFactory.core.commands;

import T06_Reflection.Exercise.P03_BarracksWarsANewFactory.interfaces.Repository;
import T06_Reflection.Exercise.P03_BarracksWarsANewFactory.interfaces.UnitFactory;

public class Retire extends Command {
    public Retire(String[] data, Repository repository, UnitFactory unitFactory) {
        super(data, repository, unitFactory);
    }

    @Override
    public String execute() {
        String unitType = super.getData()[1];
        getRepository().removeUnit(unitType);
        return String.format("%s retired!", unitType);
    }
}
