package T08_IteratorsAndComparators.Exercise.P04_Froggy;

import java.util.Iterator;

public class Lake implements Iterable<Integer>{
    private int[] stones;

    public Lake(int[] stones) {
        this.stones = stones;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            private int index = 0;
            private int lastIndex = stones.length % 2 == 0 ? stones.length - 2 : stones.length - 1;

            @Override
            public boolean hasNext() {
                return index < stones.length;
            }

            @Override
            public Integer next() {
                if (index == lastIndex){
                    index = 1;
                    return stones[lastIndex];
                }
                int element = stones[index];
                index += 2;
                return element;
            }
        };
    }
}
