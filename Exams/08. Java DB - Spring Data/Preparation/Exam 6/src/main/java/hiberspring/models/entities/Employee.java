package hiberspring.models.entities;

import javax.persistence.*;

@Entity
@Table(name = "employees")
public class Employee extends BaseEntity {
    private String firstName;
    private String lastName;
    private String position;
    private EmployeeCard card;
    private Branch branch;

    @Column(name = "first_name", nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name", nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(nullable = false)
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @OneToOne
    @JoinColumn(nullable = false)
    public EmployeeCard getCard() {
        return card;
    }

    public void setCard(EmployeeCard card) {
        this.card = card;
    }

    @ManyToOne
    @JoinColumn(nullable = false)
    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    @Override
    public String toString() {
        return String.format("Name: %s %s\n" +
                "Position: %s\n" +
                "Card Number: %s\n",
                this.getFirstName(), this.getLastName(), this.position, this.card.getNumber());
    }
}
