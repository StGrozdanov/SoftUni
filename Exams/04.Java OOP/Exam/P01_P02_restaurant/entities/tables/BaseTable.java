package restaurant.entities.tables;

import restaurant.entities.drinks.interfaces.Beverages;
import restaurant.entities.healthyFoods.interfaces.HealthyFood;
import restaurant.entities.tables.interfaces.Table;

import java.util.ArrayList;
import java.util.Collection;

import static restaurant.common.ExceptionMessages.INVALID_NUMBER_OF_PEOPLE;
import static restaurant.common.ExceptionMessages.INVALID_TABLE_SIZE;

public abstract class BaseTable implements Table {
    private Collection<HealthyFood> healthyFood;
    private Collection<Beverages> beverages;
    private int number;
    private int size;
    private int numberOfPeople;
    private double pricePerPerson;
    private boolean isReservedTable;
    private double allPeople;

    protected BaseTable(int number, int size, double pricePerPerson) {
        this.number = number;
        this.setSize(size);
        this.pricePerPerson = pricePerPerson;
        this.healthyFood = new ArrayList<>();
        this.beverages = new ArrayList<>();
        this.isReservedTable = false;
        this.setAllPeople(this.numberOfPeople, this.pricePerPerson);
    }

    private void setSize(int size) {
        if (size < 0) {
            throw new IllegalArgumentException(INVALID_TABLE_SIZE);
        }
        this.size = size;
    }

    private void setNumberOfPeople(int numberOfPeople) {
        if (numberOfPeople <= 0) {
            throw new IllegalArgumentException(INVALID_NUMBER_OF_PEOPLE);
        }
        this.numberOfPeople = numberOfPeople;
    }

    private void setAllPeople(int numberOfPeople, double pricePerPerson) {
        this.allPeople = numberOfPeople * pricePerPerson;
    }

    @Override
    public int getTableNumber() {
        return this.number;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public int numberOfPeople() {
        return this.numberOfPeople;
    }

    @Override
    public double pricePerPerson() {
        return this.pricePerPerson;
    }

    @Override
    public boolean isReservedTable() {
        return this.isReservedTable;
    }

    @Override
    public double allPeople() {
        return this.allPeople;
    }

    @Override
    public void reserve(int numberOfPeople) {
        this.isReservedTable = true;
        this.setNumberOfPeople(numberOfPeople);
        this.setAllPeople(numberOfPeople, this.pricePerPerson);
    }

    @Override
    public void orderHealthy(HealthyFood food) {
        this.healthyFood.add(food);
    }

    @Override
    public void orderBeverages(Beverages beverages) {
        this.beverages.add(beverages);
    }

    @Override
    public double bill() {
        double foodPrice = this.healthyFood.stream().mapToDouble(HealthyFood::getPrice).sum();
        double drinkPrice = this.beverages.stream().mapToDouble(Beverages::getPrice).sum();
        return foodPrice + drinkPrice + this.allPeople();
    }

    @Override
    public void clear() {
        //Removes all the ordered drinks and food and finally frees the table,
        // the table is not reserved, sets the count of people and price to 0.
        this.healthyFood.clear();
        this.beverages.clear();
        this.isReservedTable = false;
        this.numberOfPeople = 0;
        this.setAllPeople(this.numberOfPeople, this.pricePerPerson);
    }

    @Override
    public String tableInformation() {
        //"Table - {table number}"
        //"Size - {table size}"
        //"Type - {table type}"
        //"All price - {price per person for the current table}"
        return String.format("Table - %d%nSize - %d%nType - %s%nAll price - %.2f",
                this.number, this.size, this.getClass().getSimpleName(), this.pricePerPerson);
    }
}
