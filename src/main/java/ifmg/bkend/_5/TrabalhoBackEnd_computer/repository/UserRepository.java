package ifmg.bkend._5.TrabalhoBackEnd_computer.repository;

import ifmg.bkend._5.TrabalhoBackEnd_computer.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {


    @Query("{ 'userName' : ?0 }")
    User findByUserName(String userName);

    @Query("{ 'cpf' : ?0 }")
    User findByCpf(String cpf);

    @Query("{ 'email' : ?0 }")
    User findByEmail(String email);

    @Query(value = "{ 'userName' : ?0 }", delete = true)
    Long deleteByUserName(String userName);

    @Query(value = "{ 'cpf' : ?0 }", delete = true)
    Long deleteByCpf(String cpf);

    @Query(value = "{ 'email' : ?0 }", delete = true)
    Long deleteByEmail(String email);

}