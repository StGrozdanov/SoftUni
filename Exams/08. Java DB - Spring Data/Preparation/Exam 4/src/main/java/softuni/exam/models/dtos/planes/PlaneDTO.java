package softuni.exam.models.dtos.planes;

import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "plane")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class PlaneDTO {
    private String registerNumber;
    private Integer capacity;
    private String airline;

    @Size(min = 5)
    @XmlElement(name = "register-number")
    public String getRegisterNumber() {
        return registerNumber;
    }

    public void setRegisterNumber(String registerNumber) {
        this.registerNumber = registerNumber;
    }

    @PositiveOrZero
    @XmlElement
    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    @Size(min = 2)
    @XmlElement
    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }
}
