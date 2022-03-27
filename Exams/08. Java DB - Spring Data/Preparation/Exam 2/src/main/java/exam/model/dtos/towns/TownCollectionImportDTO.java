package exam.model.dtos.towns;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "towns")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class TownCollectionImportDTO {
    private List<SingleTownImportDTO> towns;

    @XmlElement(name = "town")
    public List<SingleTownImportDTO> getTowns() {
        return towns;
    }

    public void setTowns(List<SingleTownImportDTO> towns) {
        this.towns = towns;
    }
}
