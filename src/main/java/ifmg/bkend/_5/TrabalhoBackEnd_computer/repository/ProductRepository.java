package ifmg.bkend._5.TrabalhoBackEnd_computer.repository;

import ifmg.bkend._5.TrabalhoBackEnd_computer.models.Product;
import ifmg.bkend._5.TrabalhoBackEnd_computer.models.ProductType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {


    @Query("""
        {
            'brand': ?0,
            'price': { $lt: ?1 }
        }
    """)
    public List<Product> getAllProductOfBrandWithMaxCost(String brand, BigDecimal maxCost);


    @Query("""
        {
            'productType': ?0,
            'price': { $lt: ?1 }
        }
    """)
    public List<Product> getAllProductOfTypeWithMaxCost(ProductType productType, BigDecimal maxCost);


    // Usando Query Derivation ("nome do met0do")
    public List<Product> findByPriceLessThan(BigDecimal maxCost);



}
