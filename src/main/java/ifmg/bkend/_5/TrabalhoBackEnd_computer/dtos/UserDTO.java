package ifmg.bkend._5.TrabalhoBackEnd_computer.dtos;

import ifmg.bkend._5.TrabalhoBackEnd_computer.models.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

public class UserDTO {


    private String id;

    @NotBlank(message = "Campo é obrigatório")
    @Indexed(unique = true)
    private String userName;

    @NotBlank(message = "O nome real é obrigatório")
    private String nomeReal;

    @NotBlank(message = "Campo é obrigatório")
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "CPF inválido")
    private String cpf;

    @NotBlank(message = "Campo é obrigatório")
    @Indexed(unique = true)
    @Email
    private String email;

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
