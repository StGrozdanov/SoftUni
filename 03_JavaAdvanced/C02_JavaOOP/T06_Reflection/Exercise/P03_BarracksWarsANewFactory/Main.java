package T06_Reflection.Exercise.P03_BarracksWarsANewFactory;

import T06_Reflection.Exercise.P03_BarracksWarsANewFactory.core.Engine;
import T06_Reflection.Exercise.P03_BarracksWarsANewFactory.core.factories.UnitFactoryImpl;
import T06_Reflection.Exercise.P03_BarracksWarsANewFactory.data.UnitRepository;
import T06_Reflection.Exercise.P03_BarracksWarsANewFactory.interfaces.Repository;
import T06_Reflection.Exercise.P03_BarracksWarsANewFactory.interfaces.UnitFactory;

public class Main {

    public static void main(String[] args) {
        Repository repository = new UnitRepository();
        UnitFactory unitFactory = new UnitFactoryImpl();

        Runnable engine = new Engine(repository, unitFactory);
        engine.run();
    }
}
