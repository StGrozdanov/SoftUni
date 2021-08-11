package bakery.repositories;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Repository<T> implements bakery.repositories.interfaces.Repository<T> {
    private Collection<T> models;

    public Repository() {
        this.models = new ArrayList<>();
    }

    @Override
    public Collection<T> getAll() {
        return Collections.unmodifiableCollection(this.models);
    }

    @Override
    public void add(T model) {
        this.models.add(model);
    }
}
