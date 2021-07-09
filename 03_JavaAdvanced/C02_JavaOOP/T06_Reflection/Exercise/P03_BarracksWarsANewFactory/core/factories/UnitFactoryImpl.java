package T06_Reflection.Exercise.P03_BarracksWarsANewFactory.core.factories;

import T06_Reflection.Exercise.P03_BarracksWarsANewFactory.interfaces.Unit;
import T06_Reflection.Exercise.P03_BarracksWarsANewFactory.interfaces.UnitFactory;

import java.lang.reflect.InvocationTargetException;

public class UnitFactoryImpl implements UnitFactory {

	private static final String UNITS_PACKAGE_NAME = "T06_Reflection.Exercise.P03_BarracksWarsANewFactory.models.units.";

	@Override
	public Unit createUnit(String unitType){
		try {
			Class<?> clazz = Class.forName(UNITS_PACKAGE_NAME + unitType);
			return (Unit) clazz.getDeclaredConstructor().newInstance();
		} catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException
				| InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}
}
