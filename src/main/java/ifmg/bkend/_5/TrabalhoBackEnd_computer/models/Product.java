package ifmg.bkend._5.TrabalhoBackEnd_computer.models;

import ifmg.bkend._5.TrabalhoBackEnd_computer.dtos.ProductDTO;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document
public class Product {

    @Id
    private String id;

    @NotBlank(message = "Campo obrigatório")
    private String name;

    private String brand;

    private String model;

    @NotBlank(message = "Campo obrigatório")
    private BigDecimal price;

    private ProductType productType;

    private String description;


    public Product() {}

    public Product(String id, String name, String brand, String model, BigDecimal price, ProductType productType, String description) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.productType = productType;
        this.description = description;
    }

    public Product(ProductDTO dto){
        this.name = dto.getName();
        this.brand = dto.getBrand();
        this.model = dto.getModel();
        this.price = dto.getPrice();
        this.productType = ProductType.fromValue(dto.getProductType());
        this.description = dto.getDescription();
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

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
