package onlineShop.core;

import onlineShop.core.interfaces.Controller;
import onlineShop.models.products.components.*;
import onlineShop.models.products.computers.Computer;
import onlineShop.models.products.computers.DesktopComputer;
import onlineShop.models.products.computers.Laptop;
import onlineShop.models.products.peripherals.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static onlineShop.common.constants.ExceptionMessages.*;
import static onlineShop.common.constants.OutputMessages.*;

public class ControllerImpl implements Controller {
    private List<Computer> computers;
    private List<Component> components;
    private List<Peripheral> peripherals;

    public ControllerImpl() {
        this.computers = new ArrayList<>();
        this.components = new ArrayList<>();
        this.peripherals = new ArrayList<>();
    }

    @Override
    public String addComputer(String computerType, int id, String manufacturer, String model, double price) {
        Computer computer;
        if (ComputerFound(id)) {
            throw new IllegalArgumentException(EXISTING_COMPUTER_ID);
        }
        if (computerType.equals("Laptop")) {
            computer = new Laptop(id, manufacturer, model, price);
        } else if (computerType.equals("DesktopComputer")) {
            computer = new DesktopComputer(id, manufacturer, model, price);
        } else {
            throw new IllegalArgumentException(INVALID_COMPUTER_TYPE);
        }

        this.computers.add(computer);

        return String.format(ADDED_COMPUTER, id);
    }

    @Override
    public String addPeripheral(int computerId, int id, String peripheralType, String manufacturer, String model, double price, double overallPerformance, String connectionType) {
        if (!ComputerFound(computerId)) {
            throw new IllegalArgumentException(NOT_EXISTING_COMPUTER_ID);
        }
        Computer computer = findComputerById(computerId);
        Peripheral peripheral = this.peripherals.stream().filter(c -> c.getId() == id).findFirst().orElse(null);

        if (peripheral != null) {
            throw new IllegalArgumentException(EXISTING_PERIPHERAL_ID);
        }

        switch (peripheralType) {
            case "Headset":
                peripheral = new Headset(id, manufacturer, model, price, overallPerformance, connectionType);
                break;
            case "Keyboard":
                peripheral = new Keyboard(id, manufacturer, model, price, overallPerformance, connectionType);
                break;
            case "Monitor":
                peripheral = new Monitor(id, manufacturer, model, price, overallPerformance, connectionType);
                break;
            case "Mouse":
                peripheral = new Mouse(id, manufacturer, model, price, overallPerformance, connectionType);
                break;
            default:
                throw new IllegalArgumentException(INVALID_PERIPHERAL_TYPE);
        }
        this.peripherals.add(peripheral);
        computer.addPeripheral(peripheral);
        return String.format(ADDED_PERIPHERAL, peripheralType, id, computerId);
    }

    @Override
    public String removePeripheral(String peripheralType, int computerId) {
        if (!ComputerFound(computerId)) {
            throw new IllegalArgumentException(NOT_EXISTING_COMPUTER_ID);
        }
        Computer computer = findComputerById(computerId);

        Peripheral removedPeripheral = computer.removePeripheral(peripheralType);
        this.peripherals.remove(removedPeripheral);

        return String.format(REMOVED_PERIPHERAL, peripheralType, removedPeripheral.getId());
    }

    @Override
    public String addComponent(int computerId, int id, String componentType, String manufacturer, String model, double price, double overallPerformance, int generation) {
        if (!ComputerFound(computerId)) {
            throw new IllegalArgumentException(NOT_EXISTING_COMPUTER_ID);
        }
        Computer computer = findComputerById(computerId);
        Component component = this.components.stream().filter(c -> c.getId() == id).findFirst().orElse(null);

        if (component != null) {
            throw new IllegalArgumentException(EXISTING_COMPONENT_ID);
        }

        switch (componentType) {
            case "CentralProcessingUnit":
                component = new CentralProcessingUnit(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "Motherboard":
                component = new Motherboard(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "PowerSupply":
                component = new PowerSupply(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "RandomAccessMemory":
                component = new RandomAccessMemory(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "SolidStateDrive":
                component = new SolidStateDrive(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "VideoCard":
                component = new VideoCard(id, manufacturer, model, price, overallPerformance, generation);
                break;
            default:
                throw new IllegalArgumentException(INVALID_COMPONENT_TYPE);
        }
        this.components.add(component);
        computer.addComponent(component);
        return String.format(ADDED_COMPONENT, componentType, id, computerId);
    }

    @Override
    public String removeComponent(String componentType, int computerId) {
        if (!ComputerFound(computerId)) {
            throw new IllegalArgumentException(NOT_EXISTING_COMPUTER_ID);
        }
        Computer computer = findComputerById(computerId);

        Component removedComponent = computer.removeComponent(componentType);

        this.components.remove(removedComponent);

        return String.format(REMOVED_COMPONENT, componentType, removedComponent.getId());
    }

    @Override
    public String buyComputer(int id) {
        if (!ComputerFound(id)) {
            throw new IllegalArgumentException(NOT_EXISTING_COMPUTER_ID);
        }
        Computer computer = findComputerById(id);

        this.computers.remove(computer);

        return computer.toString();
    }

    @Override
    public String BuyBestComputer(double budget) {
        List<Computer> budgetComputers = this.computers.stream().filter(c -> c.getPrice() <= budget)
                .collect(Collectors.toList());

        if (this.computers.isEmpty() || budgetComputers.isEmpty()){
            throw new IllegalArgumentException(String.format(CAN_NOT_BUY_COMPUTER, budget));
        }

        double bestPerformance = budgetComputers.stream().mapToDouble(Computer::getOverallPerformance).max().getAsDouble();

        Computer bestComputer = this.computers.stream().filter(c -> c.getOverallPerformance() == bestPerformance)
                .findFirst().orElse(null);

        this.computers.remove(bestComputer);

        return bestComputer.toString();
    }

    @Override
    public String getComputerData(int id) {
        if (!ComputerFound(id)) {
            throw new IllegalArgumentException(NOT_EXISTING_COMPUTER_ID);
        }
        Computer computer = findComputerById(id);

        return computer.toString();
    }

    private boolean ComputerFound(int id) {
        Computer existingComputer = this.computers.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
        return existingComputer != null;
    }

    private Computer findComputerById(int id) {
        return this.computers.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
    }

}
