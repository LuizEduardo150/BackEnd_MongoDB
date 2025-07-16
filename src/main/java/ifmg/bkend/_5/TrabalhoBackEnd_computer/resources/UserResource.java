package ifmg.bkend._5.TrabalhoBackEnd_computer.resources;

import ifmg.bkend._5.TrabalhoBackEnd_computer.dtos.UserDTO;
import ifmg.bkend._5.TrabalhoBackEnd_computer.models.User;
import ifmg.bkend._5.TrabalhoBackEnd_computer.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
public class UserResource {

    @Autowired
    private UserService userService;



    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        /*
        List<User> users = userRepository.findAll();

        List<UserDTO> dtos = users.stream().map(UserDTO::new).toList();

        return ResponseEntity.ok().body(dtos);
         */
        return null;
    }


    @PostMapping
    public ResponseEntity<User> salvar(@Valid @RequestBody UserDTO dto) {
        /*
        try {

            User salvo = userRepository.save(new User(dto));
            return ResponseEntity.ok(salvo);
        }
        catch (DuplicateKeyException e) {
            System.out.println("Há campos com valores que devem ser únicos\n" + e);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        catch (Exception e){
            System.out.println(e.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

         */
        return null;
    }


    @GetMapping(value = "/byEml/{email}")
    public ResponseEntity<UserDTO> getByEmail(@PathVariable String email){
        /*
        System.out.println("Vamos procurar usrer com e-mail: " + email );

        User user = userRepository.getByEmailPers(email);

        if (user == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return ResponseEntity.ok().body(new UserDTO(user));

         */
        return null;
    }

    @DeleteMapping(value = {"/{email}"})
    public ResponseEntity<Void> removeByEmail(@PathVariable String email){
        /*
        System.out.println("Vamos deletar ... ");
        Long afetados = userRepository.deleteByEmailPers(email);
        if (afetados > 0)
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

         */
        return null;
    }

    @DeleteMapping(value = "/byid/{id}")
    public ResponseEntity<Void> removerById(@PathVariable String id){
       /*
        System.out.println("Vamos deletar ...");
        try {
            userRepository.deleteById(id);
        }catch (Exception e){
            System.out.println(e);
        }
        return ResponseEntity.ok().build();

        */
        return null;
    }

    @PutMapping(value = "/upByUname/{email}/{newUserName}")
    public ResponseEntity<Void> changeUserNameByEmail(@PathVariable String email, @PathVariable String newUserName){
        /*
        Query query = new Query(Criteria.where("email").is(email));
        Update update = new Update().set("userName", newUserName);
        long mod = mongoTemplate.updateFirst(query, update, User.class).getModifiedCount();

        if (mod > 0)
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

         */
        return null;
    }


}
