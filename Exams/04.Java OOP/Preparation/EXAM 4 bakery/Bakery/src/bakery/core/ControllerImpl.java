package bakery.core;

import bakery.common.ExceptionMessages;
import bakery.common.OutputMessages;
import bakery.common.enums.BakedFoodType;
import bakery.common.enums.DrinkType;
import bakery.common.enums.TableTYpe;
import bakery.core.interfaces.Controller;
import bakery.entities.bakedFoods.Bread;
import bakery.entities.bakedFoods.Cake;
import bakery.entities.bakedFoods.interfaces.BakedFood;
import bakery.entities.drinks.Tea;
import bakery.entities.drinks.Water;
import bakery.entities.drinks.interfaces.Drink;
import bakery.entities.tables.InsideTable;
import bakery.entities.tables.OutsideTable;
import bakery.entities.tables.interfaces.Table;
import bakery.repositories.interfaces.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static bakery.common.ExceptionMessages.FOOD_OR_DRINK_EXIST;
import static bakery.common.ExceptionMessages.TABLE_EXIST;
import static bakery.common.OutputMessages.*;

public class ControllerImpl implements Controller {
    private FoodRepository<BakedFood> foodRepository;
    private DrinkRepository<Drink> drinkRepository;
    private TableRepository<Table> tableRepository;
    private List<Double> collectedBills;

    public ControllerImpl(FoodRepository<BakedFood> foodRepository, DrinkRepository<Drink> drinkRepository, TableRepository<Table> tableRepository) {
        this.foodRepository = foodRepository;
        this.drinkRepository = drinkRepository;
        this.tableRepository = tableRepository;
        this.collectedBills = new ArrayList<>();
    }


    @Override
    public String addFood(String type, String name, double price) {
        if (this.foodRepository.getByName(name) != null) {
            throw new IllegalArgumentException(String.format(FOOD_OR_DRINK_EXIST, type, name));
        }
        if (BakedFoodType.Bread.name().equals(type)) {
            this.foodRepository.add(new Bread(name, price));
        } else if (BakedFoodType.Cake.name().equals(type)) {
            this.foodRepository.add(new Cake(name, price));
        }
        return String.format(FOOD_ADDED, name, type);
    }

    @Override
    public String addDrink(String type, String name, int portion, String brand) {
        if (this.drinkRepository.getByNameAndBrand(name, brand) != null) {
            throw new IllegalArgumentException(String.format(FOOD_OR_DRINK_EXIST, type, name));
        }
        if (DrinkType.Tea.name().equals(type)) {
            this.drinkRepository.add(new Tea(name, portion, brand));
        } else if (DrinkType.Water.name().equals(type)) {
            this.drinkRepository.add(new Water(name, portion, brand));
        }
        return String.format(DRINK_ADDED, name, brand);
    }

    @Override
    public String addTable(String type, int tableNumber, int capacity) {
        if (this.tableRepository.getByNumber(tableNumber) != null) {
            throw new IllegalArgumentException(String.format(TABLE_EXIST, tableNumber));
        }
        if (TableTYpe.OutsideTable.name().equals(type)) {
            this.tableRepository.add(new OutsideTable(tableNumber, capacity));
        } else if (TableTYpe.InsideTable.name().equals(type)) {
            this.tableRepository.add(new InsideTable(tableNumber, capacity));
        }
        return String.format(TABLE_ADDED, tableNumber);
    }

    @Override
    public String reserveTable(int numberOfPeople) {
        Table targetTable = this.tableRepository.getAll().stream().filter(table -> !table.isReserved()
        && table.getCapacity() >= numberOfPeople)
                .findFirst().orElse(null);

        if (targetTable == null) {
            return String.format(RESERVATION_NOT_POSSIBLE, numberOfPeople);
        }

        targetTable.reserve(numberOfPeople);

        return String.format(TABLE_RESERVED, targetTable.getTableNumber(), numberOfPeople);
    }

    @Override
    public String orderFood(int tableNumber, String foodName) {
        Table targetTable = this.tableRepository.getByNumber(tableNumber);
        BakedFood targetFood = this.foodRepository.getByName(foodName);
        if (targetTable == null) {
            return String.format(WRONG_TABLE_NUMBER, tableNumber);
        } else if (targetFood == null) {
            return String.format(NONE_EXISTENT_FOOD, foodName);
        }

        targetTable.orderFood(targetFood);

        return String.format(FOOD_ORDER_SUCCESSFUL, tableNumber, foodName);
    }

    @Override
    public String orderDrink(int tableNumber, String drinkName, String drinkBrand) {
        Table table = this.tableRepository.getByNumber(tableNumber);
        Drink drink = this.drinkRepository.getByNameAndBrand(drinkName, drinkBrand);

        if (table == null) {
            return String.format(WRONG_TABLE_NUMBER, tableNumber);
        } else if (drink == null) {
            return String.format(NON_EXISTENT_DRINK, drinkName, drinkBrand);
        }

        table.orderDrink(drink);

        return String.format(DRINK_ORDER_SUCCESSFUL, tableNumber, drinkName, drinkBrand);

    }

    @Override
    public String leaveTable(int tableNumber) {
        Table table = this.tableRepository.getByNumber(tableNumber);
        double bill = table.getBill();
        this.collectedBills.add(bill);
        table.clear();

        return String.format(BILL, tableNumber, bill);
    }

    @Override
    public String getFreeTablesInfo() {
        List<Table> tables = this.tableRepository.getAll().stream().filter(table -> !table.isReserved())
                .collect(Collectors.toList());

        StringBuilder sb = new StringBuilder();

        for (Table table : tables) {
            sb.append(table.getFreeTableInfo());
            sb.append(System.lineSeparator());
        }
        return sb.toString().trim();
    }

    @Override
    public String getTotalIncome() {
        double totalIncome = this.collectedBills.stream().mapToDouble(Double::doubleValue).sum();
        return String.format(TOTAL_INCOME, totalIncome);
    }
}
