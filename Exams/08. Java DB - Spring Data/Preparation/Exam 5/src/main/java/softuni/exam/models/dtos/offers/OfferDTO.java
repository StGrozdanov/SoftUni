package softuni.exam.models.dtos.offers;

import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlRootElement(name = "offer")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class OfferDTO {
    private String description;
    private BigDecimal price;
    private String addedOn;
    private Boolean hasGoldStatus;
    private OfferCarIdDTO car;
    private OfferSellerIdDTO seller;

    @Size(min = 5)
    @XmlElement
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @PositiveOrZero
    @XmlElement
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @XmlElement(name = "added-on")
    public String getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(String addedOn) {
        this.addedOn = addedOn;
    }

    @XmlElement(name = "has-gold-status")
    public Boolean getHasGoldStatus() {
        return hasGoldStatus;
    }

    public void setHasGoldStatus(Boolean hasGoldStatus) {
        this.hasGoldStatus = hasGoldStatus;
    }

    @XmlElement(name = "car")
    public OfferCarIdDTO getCar() {
        return car;
    }

    public void setCar(OfferCarIdDTO car) {
        this.car = car;
    }

    @XmlElement(name = "seller")
    public OfferSellerIdDTO getSeller() {
        return seller;
    }

    public void setSeller(OfferSellerIdDTO seller) {
        this.seller = seller;
    }
}
