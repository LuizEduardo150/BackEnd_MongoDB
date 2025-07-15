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
    private String clientName;

    @NotBlank(message = "Campo obrigatório")
    private Date date;

    @NotBlank(message = "Campo obrigatório")
    private List<Product> items;

    @NotBlank(message = "Campo obrigatório")
    private BigDecimal totalCost;


}
