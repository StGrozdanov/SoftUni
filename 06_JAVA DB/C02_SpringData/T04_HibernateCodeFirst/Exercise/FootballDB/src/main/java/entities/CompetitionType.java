package entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "competition_types")
public class CompetitionType extends BaseEntity {
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
