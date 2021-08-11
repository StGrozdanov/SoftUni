package ExamPreparation.P03_CollectionHierarchy.Collections;

import ExamPreparation.P03_CollectionHierarchy.Interfaces.AddRemovable;

public class AddRemoveCollection extends Collection implements AddRemovable {
    @Override
    public String remove() {
        return super.items.remove(items.size()-1);
    }

    @Override
    public int add(String element) {
        super.items.add(0, element);
        return super.items.indexOf(element);
    }
}
