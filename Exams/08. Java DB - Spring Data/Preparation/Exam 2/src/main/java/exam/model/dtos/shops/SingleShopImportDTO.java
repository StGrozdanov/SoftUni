package exam.model.dtos.shops;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlRootElement(name = "shop")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class SingleShopImportDTO {
    private String address;
    private Integer employeeCount;
    private BigDecimal income;
    private String name;
    private Integer shopArea;
    private XMLTownNameDTO town;

    @Size(min = 4)
    @NotNull
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @XmlElement(name = "employee-count")
    @Min(value = 1)
    @Max(value = 50)
    @NotNull
    public Integer getEmployeeCount() {
        return employeeCount;
    }

    public void setEmployeeCount(Integer employeeCount) {
        this.employeeCount = employeeCount;
    }

    @Min(value = 20000)
    @NotNull
    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }

    @Size(min = 4)
    @NotNull
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "shop-area")
    @Min(value = 150)
    @NotNull
    public Integer getShopArea() {
        return shopArea;
    }

    public void setShopArea(Integer shopArea) {
        this.shopArea = shopArea;
    }

    @XmlElement(name = "town")
    public XMLTownNameDTO getTown() {
        return town;
    }

    public void setTown(XMLTownNameDTO town) {
        this.town = town;
    }
}
