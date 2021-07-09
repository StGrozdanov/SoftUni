package T06_Reflection.Exercise.P03_BarracksWarsANewFactory.core;

import T06_Reflection.Exercise.P03_BarracksWarsANewFactory.interfaces.Executable;
import T06_Reflection.Exercise.P03_BarracksWarsANewFactory.interfaces.Repository;
import T06_Reflection.Exercise.P03_BarracksWarsANewFactory.interfaces.Unit;
import T06_Reflection.Exercise.P03_BarracksWarsANewFactory.interfaces.UnitFactory;
import jdk.jshell.spi.ExecutionControl;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Engine implements Runnable {

    private Repository repository;
    private UnitFactory unitFactory;
    private static final String COMMANDS_PACKAGE_NAME =
            "T06_Reflection.Exercise.P03_BarracksWarsANewFactory.core.commands.";


    public Engine(Repository repository, UnitFactory unitFactory) {
        this.repository = repository;
        this.unitFactory = unitFactory;
    }

    @Override
    public void run() {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        while (true) {
            try {
                String input = reader.readLine();
                String[] data = input.split("\\s+");
                String commandName = data[0];
                String result = interpretCommand(data, commandName);
                if (result.equals("fight")) {
                    break;
                }
                System.out.println(result);
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String interpretCommand(String[] data, String commandName) {
        String path = COMMANDS_PACKAGE_NAME + String.valueOf(commandName.charAt(0)).toUpperCase() +
                commandName.substring(1);
        try {
            Class<?> clazz = Class.forName(path);
            Constructor<?> declaredConstructor = clazz.getDeclaredConstructor(String[].class, Repository.class,
                    UnitFactory.class);
            Executable result = (Executable) declaredConstructor.newInstance(data, this.repository, this.unitFactory);
            return result.execute();
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException
                | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
