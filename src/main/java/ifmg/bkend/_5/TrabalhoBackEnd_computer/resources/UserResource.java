package ifmg.bkend._5.TrabalhoBackEnd_computer.resources; // Ou .controllers, dependendo da sua convenção

import ifmg.bkend._5.TrabalhoBackEnd_computer.dtos.UserDTO;
import ifmg.bkend._5.TrabalhoBackEnd_computer.services.UserService;
import jakarta.validation.Valid; // Importe para usar @Valid
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService userService;

    // --- Endpoints de Busca (Read Operations) ---

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        List<UserDTO> list = userService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable String id) {
        UserDTO dto = userService.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("/by-username")
    public ResponseEntity<UserDTO> findByUserName(@RequestParam String userName) {
        UserDTO dto = userService.findByUserName(userName);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("/by-cpf")
    public ResponseEntity<UserDTO> findByCpf(@RequestParam String cpf) {
        UserDTO dto = userService.findByCpf(cpf);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("/by-email")
    public ResponseEntity<UserDTO> findByEmail(@RequestParam String email) {
        UserDTO dto = userService.findByEmail(email);
        return ResponseEntity.ok().body(dto);
    }

    // --- Endpoints de Criação e Atualização (Create & Update Operations) ---

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) {
        UserDTO newDto = userService.create(userDTO);
        // Retorna 201 Created com a URI do novo recurso
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newDto.getId()).toUri();
        return ResponseEntity.created(uri).body(newDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable String id, @Valid @RequestBody UserDTO userDTO) {
        UserDTO dto = userService.update(id, userDTO);
        return ResponseEntity.ok().body(dto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserDTO> updatePartialUser(@PathVariable String id, @RequestBody UserDTO userDTO) {
        // Para PATCH, não usamos @Valid diretamente no @RequestBody,
        // pois os campos podem ser nulos. A validação é feita na lógica do Service.
        UserDTO dto = userService.updatePartial(id, userDTO);
        return ResponseEntity.ok().body(dto);
    }

    // --- Endpoints de Exclusão (Delete Operations) ---

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable String id) {
        userService.delete(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }

    @DeleteMapping("/by-username")
    public ResponseEntity<String> deleteUserByUserName(@RequestParam String userName) {
        Long deletedCount = userService.deleteByUserName(userName);
        return ResponseEntity.ok("Foram deletados " + deletedCount + " usuários com o nome de usuário: " + userName);
    }

    @DeleteMapping("/by-cpf")
    public ResponseEntity<String> deleteUserByCpf(@RequestParam String cpf) {
        Long deletedCount = userService.deleteByCpf(cpf);
        return ResponseEntity.ok("Foram deletados " + deletedCount + " usuários com o CPF: " + cpf);
    }

    @DeleteMapping("/by-email")
    public ResponseEntity<String> deleteUserByEmail(@RequestParam String email) {
        Long deletedCount = userService.deleteByEmail(email);
        return ResponseEntity.ok("Foram deletados " + deletedCount + " usuários com o email: " + email);
    }
}