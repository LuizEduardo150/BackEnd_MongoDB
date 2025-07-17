package ifmg.bkend._5.TrabalhoBackEnd_computer.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class PurchaseDTO {

    @NotBlank(message = "Nome de usuário é obrigatório")
    private String userName;

    @NotBlank(message = "deve ser comprado ao menos um item")
    private List<String> productsIds;

    public PurchaseDTO(String userName, List<String> productsIds) {
        this.userName = userName;
        this.productsIds = productsIds;
    }

    public PurchaseDTO() {}


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<String> getProductsIds() {
        return productsIds;
    }

    public void setProductsIds(List<String> productsIds) {
        this.productsIds = productsIds;
    }
}
