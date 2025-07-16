package ifmg.bkend._5.TrabalhoBackEnd_computer.dtos;

import ifmg.bkend._5.TrabalhoBackEnd_computer.models.PerformanceLevel;
import ifmg.bkend._5.TrabalhoBackEnd_computer.models.Product;
import ifmg.bkend._5.TrabalhoBackEnd_computer.models.ProductType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "Campo obrigatório")
    private BigDecimal price;

    @NotBlank(message = "Campo obrigatório")
    private String productType;

    @NotNull(message = "Campo obrigatório")
    private Integer tdp;

    @NotNull(message = "Campo obrigatório")
    private Integer quantityInStock;

    @NotNull(message = "Campo obrigatório")
    private Integer performanceLevel;

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
        this.tdp = product.getTdp();
        this.quantityInStock = product.getQuantityInStock();
        this.performanceLevel = product.getPerformanceLevel().getValue();
    }

    public ProductDTO(String id, String name, String brand, String model, BigDecimal price, String productType, Integer tdp, Integer stock, Integer performanceLevel, String description) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.productType = productType;
        this.tdp = tdp;
        this.quantityInStock = stock;
        this.description = description;
        this.performanceLevel = performanceLevel;
    }


    public Integer getTdp() {
        return tdp;
    }

    public void setTdp(Integer tdp) {
        this.tdp = tdp;
    }

    public Integer getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(Integer quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public void setPerformanceLevel(Integer performanceLevel) {
        this.performanceLevel = performanceLevel;
    }

    public PerformanceLevel getPerformanceLevel() {
        if (this.performanceLevel == null)
            return null;

        return PerformanceLevel.fromValue(performanceLevel);
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
        if (this.productType == null)
            return null;

        return ProductType.fromValue(productType);
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
