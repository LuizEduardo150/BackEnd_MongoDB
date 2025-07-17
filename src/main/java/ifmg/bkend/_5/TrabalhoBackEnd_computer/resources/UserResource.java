package ifmg.bkend._5.TrabalhoBackEnd_computer.resources; // Ou .controllers, dependendo da sua convenção

import ifmg.bkend._5.TrabalhoBackEnd_computer.dtos.UserDTO;
import ifmg.bkend._5.TrabalhoBackEnd_computer.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Usuários",
        description = "Gerenciamento de usuários da aplicação")
public class UserResource {

    @Autowired
    private UserService userService;

    // Endpoints de Busca
    @Operation(summary = "Lista todos os usuários",
            description = "Retorna uma lista completa de todos os usuários cadastrados no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de usuários retornada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDTO.class))) // Indica o tipo de retorno
    })
    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        List<UserDTO> list = userService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @Operation(summary = "Busca um usuário por ID",
            description = "Retorna um usuário específico pelo seu ID único.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDTO.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(
            @Parameter(description = "ID do usuário a ser buscado",
                    example = "60c72b2f9b1d8c001a8c4c81")
            @PathVariable String id
    ) {
        UserDTO dto = userService.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @Operation(summary = "Busca um usuário por nome de usuário",
            description = "Retorna um usuário específico pelo seu nome de usuário único.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDTO.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @GetMapping("/by-username")
    public ResponseEntity<UserDTO> findByUserName(
            @Parameter(description = "Nome de usuário a ser buscado",
                    example = "joao.silva")
            @RequestParam String userName
    ) {
        UserDTO dto = userService.findByUserName(userName);
        return ResponseEntity.ok().body(dto);
    }

    @Operation(summary = "Busca um usuário por CPF",
            description = "Retorna um usuário específico pelo seu CPF.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDTO.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @GetMapping("/by-cpf")
    public ResponseEntity<UserDTO> findByCpf(
            @Parameter(description = "CPF do usuário a ser buscado (formato: NNN.NNN.NNN-NN)",
                    example = "123.456.789-00")
            @RequestParam String cpf
    ) {
        UserDTO dto = userService.findByCpf(cpf);
        return ResponseEntity.ok().body(dto);
    }

    @Operation(summary = "Busca um usuário por e-mail",
            description = "Retorna um usuário específico pelo seu endereço de e-mail.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDTO.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @GetMapping("/by-email")
    public ResponseEntity<UserDTO> findByEmail(
            @Parameter(description = "Endereço de e-mail a ser buscado",
                    example = "joao.silva@example.com")
            @RequestParam String email
    ) {
        UserDTO dto = userService.findByEmail(email);
        return ResponseEntity.ok().body(dto);
    }

    // Endpoints de Criação e Atualização

    @Operation(summary = "Cria um novo usuário",
            description = "Registra um novo usuário no sistema com os dados fornecidos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDTO.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida (dados faltando ou mal formatados)"),
            @ApiResponse(responseCode = "409", description = "Conflito (nome de usuário, e-mail ou CPF já existente)")
    })
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) {
        UserDTO newDto = userService.create(userDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newDto.getId()).toUri();
        return ResponseEntity.created(uri).body(newDto);
    }

    @Operation(summary = "Atualiza um usuário (substituição completa)",
            description = "Atualiza todos os dados de um usuário existente pelo ID. Todos os campos devem ser fornecidos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDTO.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida (dados faltando ou mal formatados)"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado para atualização")
    })
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable String id, @Valid @RequestBody UserDTO userDTO) {
        UserDTO dto = userService.update(id, userDTO);
        return ResponseEntity.ok().body(dto);
    }

    @Operation(summary = "Atualiza parcialmente um usuário",
            description = "Atualiza um ou mais campos de um usuário existente pelo ID. Apenas os campos a serem modificados precisam ser fornecidos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário atualizado parcialmente com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDTO.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida (dados mal formatados)"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado para atualização parcial")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<UserDTO> updatePartialUser(@PathVariable String id, @RequestBody UserDTO userDTO) {
        UserDTO dto = userService.updatePartial(id, userDTO);
        return ResponseEntity.ok().body(dto);
    }

    // Endpoints de Exclusão
    @Operation(summary = "Deleta um usuário por ID",
            description = "Remove um usuário do sistema pelo seu ID único.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuário deletado com sucesso (sem conteúdo)"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado para exclusão")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(
            @Parameter(description = "ID do usuário a ser deletado",
                    example = "60c72b2f9b1d8c001a8c4c81")
            @PathVariable String id
    ) {
        userService.delete(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }

    @Operation(summary = "Deleta usuários por nome de usuário",
            description = "Remove usuários do sistema pelo nome de usuário. Retorna a contagem de usuários deletados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuários deletados com sucesso",
                    content = @Content(mediaType = "text/plain", schema = @Schema(type = "string", example = "Foram deletados 1 usuários com o nome de usuário: joao.silva"))),
            @ApiResponse(responseCode = "404", description = "Nenhum usuário encontrado para exclusão com o nome de usuário fornecido")
    })
    @DeleteMapping("/by-username")
    public ResponseEntity<String> deleteUserByUserName(
            @Parameter(description = "Nome de usuário dos usuários a serem deletados",
                    example = "joao.silva")
            @RequestParam String userName
    ) {
        Long deletedCount = userService.deleteByUserName(userName);
        return ResponseEntity.ok("Foram deletados " + deletedCount + " usuários com o nome de usuário: " + userName);
    }

    @Operation(summary = "Deleta usuários por CPF",
            description = "Remove usuários do sistema pelo CPF. Retorna a contagem de usuários deletados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuários deletados com sucesso",
                    content = @Content(mediaType = "text/plain", schema = @Schema(type = "string", example = "Foram deletados 1 usuários com o CPF: 123.456.789-00"))),
            @ApiResponse(responseCode = "404", description = "Nenhum usuário encontrado para exclusão com o CPF fornecido")
    })
    @DeleteMapping("/by-cpf")
    public ResponseEntity<String> deleteUserByCpf(
            @Parameter(description = "CPF dos usuários a serem deletados (formato: NNN.NNN.NNN-NN)",
                    example = "123.456.789-00")
            @RequestParam String cpf
    ) {
        Long deletedCount = userService.deleteByCpf(cpf);
        return ResponseEntity.ok("Foram deletados " + deletedCount + " usuários com o CPF: " + cpf);
    }

    @Operation(summary = "Deleta usuários por e-mail",
            description = "Remove usuários do sistema pelo endereço de e-mail. Retorna a contagem de usuários deletados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuários deletados com sucesso",
                    content = @Content(mediaType = "text/plain", schema = @Schema(type = "string", example = "Foram deletados 1 usuários com o email: joao.silva@example.com"))),
            @ApiResponse(responseCode = "404", description = "Nenhum usuário encontrado para exclusão com o e-mail fornecido")
    })
    @DeleteMapping("/by-email")
    public ResponseEntity<String> deleteUserByEmail(
            @Parameter(description = "Endereço de e-mail dos usuários a serem deletados",
                    example = "joao.silva@example.com")
            @RequestParam String email
    ) {
        Long deletedCount = userService.deleteByEmail(email);
        return ResponseEntity.ok("Foram deletados " + deletedCount + " usuários com o email: " + email);
    }
}