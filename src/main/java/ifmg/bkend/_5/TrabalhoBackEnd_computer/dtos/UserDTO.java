package ifmg.bkend._5.TrabalhoBackEnd_computer.dtos;

import ifmg.bkend._5.TrabalhoBackEnd_computer.models.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

public class UserDTO {

    private String id;

    //@NotBlank(message = "Campo é obrigatório")
    //@Indexed(unique = true)
    private String userName;


    //@NotBlank(message = "Campo é obrigatório")
    //@Indexed(unique = true)
    @Email
    private String email;

    private Integer age;

    public UserDTO(){}

    public UserDTO(String id, String userName, String email, Integer age) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.age = age;
    }

    public UserDTO(User user){
        this.id = user.getId();
        this.userName = user.getUserName();
        this.email = user.getEmail();
        this.age = user.getAge();
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
