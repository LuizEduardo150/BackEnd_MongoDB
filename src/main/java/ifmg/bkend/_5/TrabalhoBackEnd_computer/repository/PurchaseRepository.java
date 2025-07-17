package ifmg.bkend._5.TrabalhoBackEnd_computer.repository;

import ifmg.bkend._5.TrabalhoBackEnd_computer.models.PurchaseOrder;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface PurchaseRepository  extends MongoRepository<PurchaseOrder, String> {

}
