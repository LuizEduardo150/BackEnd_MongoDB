package ifmg.bkend._5.TrabalhoBackEnd_computer.models;

import java.math.BigDecimal;

public class BasicProductDescription {

    private String poductNameType;
    private BigDecimal price;

    public BasicProductDescription(String poductNameType, BigDecimal price) {
        this.poductNameType = poductNameType;
        this.price = price;
    }


    public BasicProductDescription(Product product) {
        this.poductNameType = product.getName() + " " +  product.getBrand() + " " + product.getModel();
        this.price = product.getPrice();
    }

    public String getPoductNameType() {
        return poductNameType;
    }

    public void setPoductNameType(String poductNameType) {
        this.poductNameType = poductNameType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
