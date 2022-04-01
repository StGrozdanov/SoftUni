package hiberspring.models.dtos.employees;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "employee")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class EmployeeDTO {
    private String firstName;
    private String lastName;
    private String position;
    private String card;
    private String branch;

    @NotNull
    @XmlAttribute(name = "first-name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @NotNull
    @XmlAttribute(name = "last-name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @NotNull
    @XmlAttribute(name = "position")
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @NotNull
    @XmlElement
    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    @NotNull
    @XmlElement
    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }
}
