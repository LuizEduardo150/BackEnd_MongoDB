package ifmg.bkend._5.TrabalhoBackEnd_computer.dtos;

import ifmg.bkend._5.TrabalhoBackEnd_computer.models.Product;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;


public class ProductDTO {

    private String id;

    @NotBlank(message = "Campo obrigatório")
    private String name;

    @NotBlank(message = "Campo obrigatório")
    private String brand;

    @NotBlank(message = "Campo obrigatório")
    private String model;

    @NotBlank(message = "Campo obrigatório")
    private BigDecimal price;

    private String productType;

    private String description;


    public ProductDTO() {}

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.brand = product.getBrand();
        this.model = product.getModel();
        this.price = product.getPrice();
        this.productType = product.getProductType().getValue();
        this.description = product.getDescription();
    }

    public ProductDTO(String id, String name, String brand, String model, BigDecimal price, String productType, String description) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.productType = productType;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
