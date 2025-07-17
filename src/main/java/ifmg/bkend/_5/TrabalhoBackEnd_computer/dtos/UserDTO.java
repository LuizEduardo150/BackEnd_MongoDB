package ifmg.bkend._5.TrabalhoBackEnd_computer.dtos;

import ifmg.bkend._5.TrabalhoBackEnd_computer.models.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

public class UserDTO {

    @Schema(description = "ID único do usuário",
            example = "60c72b2f9b1d8c001a8c4c81", accessMode = Schema.AccessMode.READ_ONLY)
    private String id;

    @NotBlank(message = "Campo é obrigatório")
    @Indexed(unique = true)
    @Schema(description = "Nome de usuário único para login",
            example = "joao.silva", requiredMode = Schema.RequiredMode.REQUIRED)
    private String userName;

    @NotBlank(message = "O nome real é obrigatório")
    @Schema(description = "Nome completo do usuário",
            example = "João da Silva", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nomeReal;

    @NotBlank(message = "Campo é obrigatório")
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "CPF inválido")
    @Schema(description = "CPF do usuário (formato: NNN.NNN.NNN-NN)",
            example = "123.456.789-00", requiredMode = Schema.RequiredMode.REQUIRED)
    private String cpf;

    @NotBlank(message = "Campo é obrigatório")
    @Indexed(unique = true)
    @Email
    @Schema(description = "Endereço de e-mail único do usuário",
            example = "joao.silva@example.com", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

    @Schema(description = "Idade do usuário", example = "30")
    private Integer age;

    public UserDTO(){}

    public UserDTO(String id, String userName, String nomeReal, String cpf, String email, Integer age) {
        this.id = id;
        this.userName = userName;
        this.nomeReal = nomeReal;
        this.cpf = cpf;
        this.email = email;
        this.age = age;
    }

    public UserDTO(User user){
        this.id = user.getId();
        this.userName = user.getUserName();
        this.nomeReal = user.getNomeReal();
        this.cpf = user.getCpf();
        this.email = user.getEmail();
        this.age = user.getAge();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNomeReal() {
        return nomeReal;
    }

    public void setNomeReal(String nomeReal) {
        this.nomeReal = nomeReal;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
