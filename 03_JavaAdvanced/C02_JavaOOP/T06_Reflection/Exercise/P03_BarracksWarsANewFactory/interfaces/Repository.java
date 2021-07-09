package T06_Reflection.Exercise.P03_BarracksWarsANewFactory.interfaces;

public interface Repository {

	void addUnit(Unit unit);

	String getStatistics();

	void removeUnit(String unitType);
}
