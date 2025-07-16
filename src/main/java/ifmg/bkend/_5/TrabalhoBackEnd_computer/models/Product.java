package ifmg.bkend._5.TrabalhoBackEnd_computer.models;

import ifmg.bkend._5.TrabalhoBackEnd_computer.dtos.ProductDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document
public class Product {

    @Id
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
    private ProductType productType;

    @NotNull(message = "Campo obrigatório")
    private Integer tdp;

    @NotNull(message = "Campo obrigatório")
    private Integer quantityInStock;

    private String description;

    @NotBlank(message = "Campo obrigatório")
    private PerformanceLevel performanceLevel;


    public Product() {}

    public Product(String id, String name, String brand, String model, BigDecimal price, ProductType productType, Integer tdp, Integer stock, PerformanceLevel performanceLevel, String description) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.productType = productType;
        this.tdp = tdp;
        this.quantityInStock = stock;
        this.performanceLevel = performanceLevel;
        this.description = description;
    }

    public Product(String name, String brand, String model, BigDecimal price, String productTypeCode, Integer tdp, Integer stock, Integer performanceLevelCode, String description) {
        this.name = name;
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.productType = ProductType.fromValue(productTypeCode);
        this.tdp = tdp;
        this.quantityInStock = stock;
        this.performanceLevel = PerformanceLevel.fromValue(performanceLevelCode);
        this.description = description;
    }

    public Product(ProductDTO dto){
        this.name = dto.getName();
        this.brand = dto.getBrand();
        this.model = dto.getModel();
        this.price = dto.getPrice();
        this.productType = dto.getProductType();
        this.tdp = dto.getTdp();
        this.quantityInStock = dto.getQuantityInStock();
        this.performanceLevel = dto.getPerformanceLevel();
        this.description = dto.getDescription();
    }


    public PerformanceLevel getPerformanceLevel() {
        return performanceLevel;
    }

    public void setPerformanceLevel(PerformanceLevel performanceLevel) {
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
