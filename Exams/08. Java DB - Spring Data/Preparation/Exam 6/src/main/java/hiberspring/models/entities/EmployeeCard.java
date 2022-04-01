package hiberspring.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "employee_cards")
public class EmployeeCard extends BaseEntity {
    private String number;

    @Column(unique = true, nullable = false)
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
