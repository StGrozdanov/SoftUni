package T06_Reflection.Exercise.P03_BarracksWarsANewFactory.interfaces;

import jdk.jshell.spi.ExecutionControl;

public interface UnitFactory {
    Unit createUnit(String unitType) throws ExecutionControl.NotImplementedException;
}