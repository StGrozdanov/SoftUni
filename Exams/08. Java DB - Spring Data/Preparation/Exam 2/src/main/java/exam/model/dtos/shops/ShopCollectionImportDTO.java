package exam.model.dtos.shops;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "shops")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class ShopCollectionImportDTO {
    private List<SingleShopImportDTO> shops;

    @XmlElement(name = "shop")
    public List<SingleShopImportDTO> getShops() {
        return shops;
    }

    public void setShops(List<SingleShopImportDTO> shops) {
        this.shops = shops;
    }
}
