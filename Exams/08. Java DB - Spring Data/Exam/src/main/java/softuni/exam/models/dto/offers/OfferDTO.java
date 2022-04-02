package softuni.exam.models.dto.offers;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlRootElement(name = "offer")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class OfferDTO {
    private BigDecimal price;
    private AgentXMLDTO agent;
    private ApartmentXMLDTO apartment;
    private String publishedOn;

    @XmlElement
    @NotNull
    @Positive
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @XmlElement(name = "agent")
    public AgentXMLDTO getAgent() {
        return agent;
    }

    public void setAgent(AgentXMLDTO agent) {
        this.agent = agent;
    }

    @XmlElement(name = "apartment")
    public ApartmentXMLDTO getApartment() {
        return apartment;
    }

    public void setApartment(ApartmentXMLDTO apartment) {
        this.apartment = apartment;
    }

    @XmlElement
    @NotNull
    public String getPublishedOn() {
        return publishedOn;
    }

    public void setPublishedOn(String publishedOn) {
        this.publishedOn = publishedOn;
    }
}
