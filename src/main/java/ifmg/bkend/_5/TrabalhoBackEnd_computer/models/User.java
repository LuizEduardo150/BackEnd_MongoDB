package ifmg.bkend._5.TrabalhoBackEnd_computer.models;

import ifmg.bkend._5.TrabalhoBackEnd_computer.dtos.UserDTO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class User {

    @Id
    private String id;

    @NotBlank(message = "Campo é obrigatório")
    @Indexed(unique = true)
    private String userName;

    @Email
    @NotBlank(message = "Campo é obrigatório")
    @Indexed(unique = true)
    private String email;

    private Integer age;

    public User(){}

    public User(UserDTO dto){
        this.id = dto.getId();
        this.userName = dto.getUserName();
        this.email = dto.getEmail();
        this.age = dto.getAge();
    }

    public User(String id, String userName, String email, Integer age) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.age = age;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
