package softuni.exam.models.dtos.tickets;

import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlRootElement(name = "ticket")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class TicketDTO {
    private String serialNumber;
    private BigDecimal price;
    private String takeOff;
    private FromTownNameDTO fromTown;
    private ToTownNameDTO toTown;
    private PassengerEmailDTO passenger;
    private PlaneRegisterNumberDTO plane;

    @Size(min = 2)
    @XmlElement(name = "serial-number")
    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    @PositiveOrZero
    @XmlElement
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @XmlElement(name = "take-off")
    public String getTakeOff() {
        return takeOff;
    }

    public void setTakeOff(String takeOff) {
        this.takeOff = takeOff;
    }

    @XmlElement(name = "from-town")
    public FromTownNameDTO getFromTown() {
        return fromTown;
    }

    public void setFromTown(FromTownNameDTO fromTown) {
        this.fromTown = fromTown;
    }

    @XmlElement(name = "to-town")
    public ToTownNameDTO getToTown() {
        return toTown;
    }

    public void setToTown(ToTownNameDTO toTown) {
        this.toTown = toTown;
    }

    @XmlElement(name = "passenger")
    public PassengerEmailDTO getPassenger() {
        return passenger;
    }

    public void setPassenger(PassengerEmailDTO passenger) {
        this.passenger = passenger;
    }

    @XmlElement(name = "plane")
    public PlaneRegisterNumberDTO getPlane() {
        return plane;
    }

    public void setPlane(PlaneRegisterNumberDTO plane) {
        this.plane = plane;
    }
}
