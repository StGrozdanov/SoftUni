package softuni.exam.models.dto.apartments;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "apartments")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class ApartmentCollectionImportDTO {
    private List<ApartmentDTO> apartments;

    @XmlElement(name = "apartment")
    public List<ApartmentDTO> getApartments() {
        return apartments;
    }

    public void setApartments(List<ApartmentDTO> apartments) {
        this.apartments = apartments;
    }
}
