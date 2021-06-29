package T03_Inheritance.Lab.P04_RandomArrayList;

import java.util.ArrayList;
import java.util.Random;

public class RandomArrayList extends ArrayList<Object> {
    public Object getRandomElement(){
        Random random = new Random();
        int index = random.nextInt(super.size());
        return remove(index);
    }
}
