package onlineShop.models.products.computers;

import onlineShop.models.products.BaseProduct;
import onlineShop.models.products.components.Component;
import onlineShop.models.products.peripherals.Peripheral;

import java.util.ArrayList;
import java.util.List;

import static onlineShop.common.constants.ExceptionMessages.*;
import static onlineShop.common.constants.OutputMessages.COMPUTER_COMPONENTS_TO_STRING;
import static onlineShop.common.constants.OutputMessages.COMPUTER_PERIPHERALS_TO_STRING;

public abstract class BaseComputer extends BaseProduct implements Computer {
    private List<Component> components;
    private List<Peripheral> peripherals;

    protected BaseComputer(int id, String manufacturer, String model, double price, double overallPerformance) {
        super(id, manufacturer, model, price, overallPerformance);
        this.components = new ArrayList<>();
        this.peripherals = new ArrayList<>();
    }

    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public String getManufacturer() {
        return super.getManufacturer();
    }

    @Override
    public String getModel() {
        return super.getModel();
    }

    @Override
    public double getPrice() {
        double totalComponentsPrice = this.components.stream().mapToDouble(Component::getPrice).sum();
        double totalPeripheralsPrice = this.peripherals.stream().mapToDouble(Peripheral::getPrice).sum();

        return super.getPrice() + totalComponentsPrice + totalPeripheralsPrice;
    }

    private double calculateOverallPerformance() {
        if (components.isEmpty()) {
            return super.getOverallPerformance();
        } else {
            double avgOverallComponentPerformance = this.components
                    .stream()
                    .mapToDouble(Component::getOverallPerformance)
                    .average()
                    .getAsDouble();

            return super.getOverallPerformance() + avgOverallComponentPerformance;
        }
    }

    @Override
    public double getOverallPerformance() {
        return this.calculateOverallPerformance();
    }

    @Override
    public List<Component> getComponents() {
        return this.components;
    }

    @Override
    public List<Peripheral> getPeripherals() {
        return this.peripherals;
    }

    @Override
    public void addComponent(Component component) {
        String componentType = component.getClass().getSimpleName();
        Component componentFound = this.components.stream()
                .filter(c -> c.getClass().getSimpleName().equals(componentType)).findFirst().orElse(null);
        if (componentFound != null) {
            throw new IllegalArgumentException(String.format(EXISTING_COMPONENT, componentType,
                    this.getClass().getSimpleName(), this.getId()));
        }
        this.components.add(component);
    }

    @Override
    public Component removeComponent(String componentType) {
        Component componentToRemove = this.components.stream().filter(c -> c.getClass().getSimpleName()
                .equals(componentType)).findFirst().orElse(null);
        if (componentToRemove == null) {
            throw new IllegalArgumentException(String.format(NOT_EXISTING_COMPONENT, componentType,
                    this.getClass().getSimpleName(), this.getId()));
        }

        this.components.remove(componentToRemove);

        return componentToRemove;
    }

    @Override
    public void addPeripheral(Peripheral peripheral) {
        String peripheralType = peripheral.getClass().getSimpleName();
        Peripheral peripheralFound = this.peripherals.stream()
                .filter(c -> c.getClass().getSimpleName().equals(peripheralType)).findFirst().orElse(null);
        if (peripheralFound != null) {
            throw new IllegalArgumentException(String.format(EXISTING_PERIPHERAL, peripheralType,
                    this.getClass().getSimpleName(), this.getId()));
        }
        this.peripherals.add(peripheral);
    }

    @Override
    public Peripheral removePeripheral(String peripheralType) {
        Peripheral peripheralToRemove = this.peripherals.stream().filter(c -> c.getClass().getSimpleName()
                .equals(peripheralType)).findFirst().orElse(null);
        if (peripheralToRemove == null) {
            throw new IllegalArgumentException(String.format(NOT_EXISTING_PERIPHERAL, peripheralType,
                    this.getClass().getSimpleName(), this.getId()));
        }

        this.peripherals.remove(peripheralToRemove);

        return peripheralToRemove;
    }

    @Override
    public String toString() {

        double avgOverallPeripheralPerformance;

        if (this.peripherals.isEmpty()) {
            avgOverallPeripheralPerformance = 0;
        } else {
            avgOverallPeripheralPerformance = this.peripherals
                    .stream()
                    .mapToDouble(Peripheral::getOverallPerformance)
                    .average()
                    .getAsDouble();
        }
        StringBuilder sb = new StringBuilder();
        sb
                .append(String.format(" " + COMPUTER_COMPONENTS_TO_STRING, this.components.size()))
                .append(System.lineSeparator());
        for (Component component : components) {
            sb
                    .append(String.format("  %s", component.toString()))
                    .append(System.lineSeparator());
        }
        sb
                .append(String.format(" " + COMPUTER_PERIPHERALS_TO_STRING,
                        this.peripherals.size(), avgOverallPeripheralPerformance))
                .append(System.lineSeparator());
        int counter = 0;
        for (Peripheral peripheral : peripherals) {
            counter++;
            sb
                    .append(String.format("  %s", peripheral.toString()));
            if (counter < peripherals.size()) {
                sb.append(System.lineSeparator());
            }
        }

        return super.toString() + System.lineSeparator() + sb;
    }
}
