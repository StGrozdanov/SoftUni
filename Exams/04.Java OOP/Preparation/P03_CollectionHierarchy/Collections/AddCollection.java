package ExamPreparation.P03_CollectionHierarchy.Collections;

import ExamPreparation.P03_CollectionHierarchy.Interfaces.Addable;

public class AddCollection extends Collection implements Addable {
    @Override
    public int add(String element) {
        super.items.add(element);
        return super.items.indexOf(element);
    }
}
