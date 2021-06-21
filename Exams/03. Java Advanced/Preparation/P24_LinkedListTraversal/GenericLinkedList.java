package MidExamPreparation.P24_LinkedListTraversal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GenericLinkedList<T> implements Iterable<T> {
    private List<T> list;

    public GenericLinkedList() {
        this.list = new ArrayList<>();
    }

    public void add(T element){
        this.list.add(element);
    }

    public boolean remove(T element){
        for (T e : list) {
            if (e.equals(element)){
                this.list.remove(e);
                return true;
            }
        }
        return false;
    }

    public void getSize(){
        System.out.println(list.size());
    }

    @Override
    public Iterator<T> iterator() {
        return new iterate();
    }

    private class iterate implements Iterator<T> {
        private int counter = 0;

        @Override
        public boolean hasNext() {
            return counter < list.size();
        }

        @Override
        public T next() {
            return list.get(counter++);
        }
    }
}
