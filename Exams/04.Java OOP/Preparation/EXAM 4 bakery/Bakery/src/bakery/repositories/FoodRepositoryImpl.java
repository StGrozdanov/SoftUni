package bakery.repositories;

import bakery.entities.bakedFoods.interfaces.BakedFood;
import bakery.repositories.interfaces.FoodRepository;

public class FoodRepositoryImpl extends Repository<BakedFood> implements FoodRepository<BakedFood> {
    @Override
    public BakedFood getByName(String name) {
        return super.getAll().stream().filter(food -> food.getName().equals(name)).findFirst().orElse(null);
    }
}
