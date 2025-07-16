package ifmg.bkend._5.TrabalhoBackEnd_computer.repository;

import ifmg.bkend._5.TrabalhoBackEnd_computer.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    // --- Métodos de Busca (Read Operations) ---

    /**
     * Busca um usuário pelo nome de usuário usando uma consulta personalizada no MongoDB.
     * Equivalente a db.users.findOne({ 'userName' : 'valor' })
     * @param userName O nome de usuário do usuário a ser buscado.
     * @return O objeto User encontrado, ou null se não for encontrado.
     */
    @Query("{ 'userName' : ?0 }")
    User findByUserName(String userName);

    /**
     * Busca um usuário pelo CPF usando uma consulta personalizada no MongoDB.
     * Equivalente a db.users.findOne({ 'cpf' : 'valor' })
     * @param cpf O CPF do usuário a ser buscado.
     * @return O objeto User encontrado, ou null se não for encontrado.
     */
    @Query("{ 'cpf' : ?0 }")
    User findByCpf(String cpf);

    /**
     * Busca um usuário pelo email usando uma consulta personalizada no MongoDB.
     * Equivalente a db.users.findOne({ 'email' : 'valor' })
     * @param email O email do usuário a ser buscado.
     * @return O objeto User encontrado, ou null se não for encontrado.
     */
    @Query("{ 'email' : ?0 }")
    User findByEmail(String email);

    // Nota: findById(String id) e findAll() são herdados de MongoRepository e não precisam ser declarados aqui.
    // existsById(String id) também é herdado.

    // --- Métodos de Exclusão (Delete Operations) ---

    /**
     * Deleta um ou mais usuários pelo nome de usuário usando uma consulta personalizada no MongoDB.
     * Retorna o número de documentos deletados.
     * Equivalente a db.users.deleteMany({ 'userName' : 'valor' })
     * @param userName O nome de usuário dos usuários a serem deletados.
     * @return O número de usuários deletados.
     */
    @Query(value = "{ 'userName' : ?0 }", delete = true)
    Long deleteByUserName(String userName);

    /**
     * Deleta um ou mais usuários pelo CPF usando uma consulta personalizada no MongoDB.
     * Retorna o número de documentos deletados.
     * Equivalente a db.users.deleteMany({ 'cpf' : 'valor' })
     * @param cpf O CPF dos usuários a serem deletados.
     * @return O número de usuários deletados.
     */
    @Query(value = "{ 'cpf' : ?0 }", delete = true)
    Long deleteByCpf(String cpf);

    /**
     * Deleta um ou mais usuários pelo email usando uma consulta personalizada no MongoDB.
     * Retorna o número de documentos deletados.
     * Equivalente a db.users.deleteMany({ 'email' : 'valor' })
     * @param email O email dos usuários a serem deletados.
     * @return O número de usuários deletados.
     */
    @Query(value = "{ 'email' : ?0 }", delete = true)
    Long deleteByEmail(String email);

    // Nota: deleteById(String id) é herdado de MongoRepository e não precisa ser declarado aqui.
}