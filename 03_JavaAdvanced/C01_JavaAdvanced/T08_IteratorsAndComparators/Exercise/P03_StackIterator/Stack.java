package T08_IteratorsAndComparators.Exercise.P03_StackIterator;

import java.util.ArrayDeque;
import java.util.Iterator;

public class Stack implements Iterable<Integer> {
    private ArrayDeque<Integer> stack;

    public Stack() {
        this.stack = new ArrayDeque<>();
    }

    public void push(int... num){
        for (int number : num) {
            this.stack.push(number);
        }
    }

    public int pop(){
        if (this.stack.isEmpty()){
            System.out.println("No elements");
            return -1;
        }
            return this.stack.pop();
    }

    public boolean isEmpty(){
        return stack.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Integer integer : stack) {
            builder.append(integer).append(System.lineSeparator());
        }
        return builder.toString().trim();
    }

    @Override
    public Iterator<Integer> iterator() {
        return stack.iterator();
    }
}
