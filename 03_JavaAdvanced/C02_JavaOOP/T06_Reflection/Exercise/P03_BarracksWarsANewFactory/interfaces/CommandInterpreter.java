package T06_Reflection.Exercise.P03_BarracksWarsANewFactory.interfaces;

public interface CommandInterpreter {

	Executable interpretCommand(String[] data, String commandName);
}
