package ifmg.bkend._5.TrabalhoBackEnd_computer.repository;

import ifmg.bkend._5.TrabalhoBackEnd_computer.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends MongoRepository<User, String> {

    // ?0 -> Primeiro parametro ... ?N ...
    @Query("{ 'email' : ?0 }")
    public User getByEmailPers(String email);

    @Query(value = "{ 'email' : ?0 }", delete = true)
    public Long deleteByEmailPers(String email);




}
