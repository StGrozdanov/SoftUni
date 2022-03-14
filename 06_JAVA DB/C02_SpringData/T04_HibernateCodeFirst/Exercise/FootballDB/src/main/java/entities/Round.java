package entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "rounds")
public class Round extends BaseEntity {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
