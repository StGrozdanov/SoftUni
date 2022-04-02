package softuni.exam.models.dto.apartments;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "apartment")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class ApartmentDTO {
    private String apartmentType;
    private Double area;
    private String town;

    @XmlElement
    @NotNull
    public String getApartmentType() {
        return apartmentType;
    }

    public void setApartmentType(String apartmentType) {
        this.apartmentType = apartmentType;
    }

    @DecimalMin(value = "40.00")
    @NotNull
    @XmlElement
    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    @Size(min = 2)
    @NotNull
    @XmlElement
    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }
}
