package ifmg.bkend._5.TrabalhoBackEnd_computer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private MongoTemplate mongoTemplate;



}
