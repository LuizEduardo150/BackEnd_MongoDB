package ifmg.bkend._5.TrabalhoBackEnd_computer.services;

import ifmg.bkend._5.TrabalhoBackEnd_computer.dtos.UserDTO;
import ifmg.bkend._5.TrabalhoBackEnd_computer.models.User;
import ifmg.bkend._5.TrabalhoBackEnd_computer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // --- Métodos de Busca (Read Operations) ---

    public List<UserDTO> findAll() {
        return userRepository.findAll().stream()
                .map(UserDTO::new)
                .collect(Collectors.toList());
    }

    public UserDTO findById(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado com o ID: " + id));
        return new UserDTO(user);
    }

    public UserDTO findByUserName(String userName) {
        User user = userRepository.findByUserName(userName);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado com o nome de usuário: " + userName);
        }
        return new UserDTO(user);
    }

    public UserDTO findByCpf(String cpf) {
        User user = userRepository.findByCpf(cpf);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado com o CPF: " + cpf);
        }
        return new UserDTO(user);
    }

    public UserDTO findByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado com o email: " + email);
        }
        return new UserDTO(user);
    }

    // --- Métodos de Criação e Atualização (Create & Update Operations) ---

    public UserDTO create(UserDTO userDTO) {
        User user = new User(userDTO);

        // Validações de unicidade antes de salvar
        if (userRepository.findByUserName(user.getUserName()) != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Nome de usuário '" + user.getUserName() + "' já existe.");
        }
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email '" + user.getEmail() + "' já existe.");
        }
        if (userRepository.findByCpf(user.getCpf()) != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "CPF '" + user.getCpf() + "' já existe.");
        }

        User savedUser = userRepository.save(user);
        return new UserDTO(savedUser);
    }

    public UserDTO update(String id, UserDTO userDTO) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado para atualização com o ID: " + id));

        // Atualiza os campos do usuário existente com os dados do DTO
        existingUser.setUserName(userDTO.getUserName());
        existingUser.setNomeReal(userDTO.getNomeReal());
        existingUser.setCpf(userDTO.getCpf());
        existingUser.setEmail(userDTO.getEmail());
        existingUser.setAge(userDTO.getAge());

        User updatedUser = userRepository.save(existingUser);
        return new UserDTO(updatedUser);
    }

    public UserDTO updatePartial(String id, UserDTO userDTO) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado para atualização parcial com o ID: " + id));

        // Verifica cada campo do DTO. Se não for nulo, atualiza o campo correspondente no usuário existente.
        if (userDTO.getUserName() != null) {
            // Opcional: Adicionar validação de unicidade aqui se o userName for atualizado
            // if (userRepository.findByUserName(userDTO.getUserName()) != null && !userDTO.getUserName().equals(existingUser.getUserName())) {
            //    throw new ResponseStatusException(HttpStatus.CONFLICT, "Nome de usuário '" + userDTO.getUserName() + "' já existe.");
            // }
            existingUser.setUserName(userDTO.getUserName());
        }
        if (userDTO.getNomeReal() != null) {
            existingUser.setNomeReal(userDTO.getNomeReal());
        }
        if (userDTO.getCpf() != null) {
            // Opcional: Adicionar validação de unicidade aqui se o CPF for atualizado
            // if (userRepository.findByCpf(userDTO.getCpf()) != null && !userDTO.getCpf().equals(existingUser.getCpf())) {
            //     throw new ResponseStatusException(HttpStatus.CONFLICT, "CPF '" + userDTO.getCpf() + "' já existe.");
            // }
            existingUser.setCpf(userDTO.getCpf());
        }
        if (userDTO.getEmail() != null) {
            // Opcional: Adicionar validação de unicidade aqui se o email for atualizado
            // if (userRepository.findByEmail(userDTO.getEmail()) != null && !userDTO.getEmail().equals(existingUser.getEmail())) {
            //    throw new ResponseStatusException(HttpStatus.CONFLICT, "Email '" + userDTO.getEmail() + "' já existe.");
            // }
            existingUser.setEmail(userDTO.getEmail());
        }
        if (userDTO.getAge() != null) { // Para Integer, 'null' é a verificação correta.
            existingUser.setAge(userDTO.getAge());
        }

        User updatedUser = userRepository.save(existingUser); // Salva as alterações
        return new UserDTO(updatedUser);
    }

    // --- Métodos de Exclusão (Delete Operations) ---

    public void delete(String id) {
        if (!userRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado para exclusão com o ID: " + id);
        }
        userRepository.deleteById(id);
    }

    public Long deleteByUserName(String userName) {
        Long deletedCount = userRepository.deleteByUserName(userName);
        if (deletedCount == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum usuário encontrado com o nome de usuário: " + userName + " para exclusão.");
        }
        return deletedCount;
    }

    public Long deleteByCpf(String cpf) {
        Long deletedCount = userRepository.deleteByCpf(cpf);
        if (deletedCount == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum usuário encontrado com o CPF: " + cpf + " para exclusão.");
        }
        return deletedCount;
    }

    public Long deleteByEmail(String email) {
        Long deletedCount = userRepository.deleteByEmail(email);
        if (deletedCount == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum usuário encontrado com o email: " + email + " para exclusão.");
        }
        return deletedCount;
    }
}