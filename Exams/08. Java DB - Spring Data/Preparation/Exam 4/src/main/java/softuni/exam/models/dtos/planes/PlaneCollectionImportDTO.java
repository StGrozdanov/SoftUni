package softuni.exam.models.dtos.planes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "planes")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class PlaneCollectionImportDTO {
    private List<PlaneDTO> planes;

    @XmlElement(name = "plane")
    public List<PlaneDTO> getPlanes() {
        return planes;
    }

    public void setPlanes(List<PlaneDTO> planes) {
        this.planes = planes;
    }
}
