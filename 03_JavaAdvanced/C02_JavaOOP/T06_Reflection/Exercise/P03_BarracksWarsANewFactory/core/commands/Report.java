package T06_Reflection.Exercise.P03_BarracksWarsANewFactory.core.commands;

import T06_Reflection.Exercise.P03_BarracksWarsANewFactory.interfaces.Repository;
import T06_Reflection.Exercise.P03_BarracksWarsANewFactory.interfaces.UnitFactory;

public class Report extends Command{
    public Report(String[] data, Repository repository, UnitFactory unitFactory) {
        super(data, repository, unitFactory);
    }

    @Override
    public String execute() {
        return super.getRepository().getStatistics();
    }
}
