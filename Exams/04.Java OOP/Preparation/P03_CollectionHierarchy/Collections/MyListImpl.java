package ExamPreparation.P03_CollectionHierarchy.Collections;

import ExamPreparation.P03_CollectionHierarchy.Interfaces.MyList;

public class MyListImpl extends Collection implements MyList {

    @Override
    public int add(String element){
        super.items.add(0, element);
        return super.items.indexOf(element);
    }

    @Override
    public String remove() {
        return super.items.remove(0);
    }

    @Override
    public int getUsed() {
        return super.items.size();
    }
}
