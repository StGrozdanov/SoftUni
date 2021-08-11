package bakery.repositories;

import bakery.entities.drinks.interfaces.Drink;
import bakery.repositories.interfaces.DrinkRepository;

public class DrinkRepositoryImpl extends Repository<Drink> implements DrinkRepository<Drink> {
    @Override
    public Drink getByNameAndBrand(String drinkName, String drinkBrand) {
        return super.getAll().stream().filter(drink -> drink.getBrand().equals(drinkBrand)
                && drink.getName().equals(drinkName)).findFirst().orElse(null);
    }
}
