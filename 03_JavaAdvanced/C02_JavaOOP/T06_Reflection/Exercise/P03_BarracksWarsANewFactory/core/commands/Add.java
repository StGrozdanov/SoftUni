package T06_Reflection.Exercise.P03_BarracksWarsANewFactory.core.commands;

import T06_Reflection.Exercise.P03_BarracksWarsANewFactory.interfaces.Repository;
import T06_Reflection.Exercise.P03_BarracksWarsANewFactory.interfaces.Unit;
import T06_Reflection.Exercise.P03_BarracksWarsANewFactory.interfaces.UnitFactory;
import jdk.jshell.spi.ExecutionControl;

public class Add extends Command {

    public Add(String[] data, Repository repository, UnitFactory unitFactory) {
        super(data, repository, unitFactory);
    }

    @Override
    public String execute() {
        String unitType = super.getData()[1];
        Unit unitToAdd = null;
        try {
            unitToAdd = super.getUnitFactory().createUnit(unitType);
        } catch (ExecutionControl.NotImplementedException e) {
            e.printStackTrace();
        }
        super.getRepository().addUnit(unitToAdd);
        return unitType + " added!";
    }
}
