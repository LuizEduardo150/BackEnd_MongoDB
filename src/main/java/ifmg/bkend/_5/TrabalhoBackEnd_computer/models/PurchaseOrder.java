package ifmg.bkend._5.TrabalhoBackEnd_computer.models;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Document
public class PurchaseOrder {

    @Id
    private String id;

    @NotBlank(message = "Campo obrigatório")
    private List<BasicProductDescription> idItems;

    @NotBlank(message = "Campo obrigatório")
    private BigDecimal totalCost;

    @NotBlank(message = "Campo obrigatório")
    private Date date;

    @NotBlank(message = "Campo obrigatório")
    private String clientName;

    @NotBlank(message = "Campo obrigatório")
    private String clientUserName;

    @NotBlank(message = "Campo obrigatório")
    private String clientEmail;

    public PurchaseOrder(String id, List<BasicProductDescription> idItems, BigDecimal totalCost, Date date, String clientName, String clientUserName, String clientEmail) {
        this.id = id;
        this.idItems = idItems;
        this.totalCost = totalCost;
        this.date = date;
        this.clientName = clientName;
        this.clientUserName = clientUserName;
        this.clientEmail = clientEmail;
    }
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<BasicProductDescription> getIdItems() {
        return idItems;
    }

    public void setIdItems(List<BasicProductDescription> idItems) {
        this.idItems = idItems;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientUserName() {
        return clientUserName;
    }

    public void setClientUserName(String clientUserName) {
        this.clientUserName = clientUserName;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }
}
