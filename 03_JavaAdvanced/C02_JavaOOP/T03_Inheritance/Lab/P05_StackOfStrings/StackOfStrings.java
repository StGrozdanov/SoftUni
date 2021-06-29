package T03_Inheritance.Lab.P05_StackOfStrings;

import java.util.ArrayList;

public class StackOfStrings {
    private ArrayList<String> data;

    public StackOfStrings() {
        this.data = new ArrayList<>();
    }

    public void push(String item){
        this.data.add(item);
    }
    public String pop(){
        if (!isEmpty()) {
            return data.remove(data.size() - 1);
        }
        return null;
    }
    public String peek(){
        if (!isEmpty()) {
            return data.get(data.size() - 1);
        }
        return null;
    }
    public boolean isEmpty(){
        return data.isEmpty();
    }
}
