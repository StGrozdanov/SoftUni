package softuni.exam.models.dto.offers;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "apartment")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class ApartmentXMLDTO {
    private Long id;

    @XmlElement
    @NotNull
    @Positive
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
