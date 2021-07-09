package T06_Reflection.Exercise.P03_BarracksWarsANewFactory.core.commands;

import T06_Reflection.Exercise.P03_BarracksWarsANewFactory.interfaces.Executable;
import T06_Reflection.Exercise.P03_BarracksWarsANewFactory.interfaces.Repository;
import T06_Reflection.Exercise.P03_BarracksWarsANewFactory.interfaces.UnitFactory;

public abstract class Command implements Executable {
    private String[] data;
    private Repository repository;
    private UnitFactory unitFactory;

    protected Command(String[] data, Repository repository, UnitFactory unitFactory) {
        this.data = data;
        this.repository = repository;
        this.unitFactory = unitFactory;
    }

    protected Repository getRepository() {
        return this.repository;
    }

    protected UnitFactory getUnitFactory() {
        return this.unitFactory;
    }

    protected String[] getData() {
        return this.data;
    }

}
