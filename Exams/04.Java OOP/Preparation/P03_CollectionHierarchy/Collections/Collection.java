package ExamPreparation.P03_CollectionHierarchy.Collections;

import java.util.ArrayList;
import java.util.List;

public abstract class Collection {
    protected final int maxSize = 100;
    protected List<String> items;

    protected Collection(){
        this.items = new ArrayList<>(maxSize);
    }
}
