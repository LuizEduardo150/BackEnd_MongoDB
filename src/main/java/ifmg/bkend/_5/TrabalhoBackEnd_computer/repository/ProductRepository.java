package ifmg.bkend._5.TrabalhoBackEnd_computer.repository;

import ifmg.bkend._5.TrabalhoBackEnd_computer.models.Product;
import ifmg.bkend._5.TrabalhoBackEnd_computer.models.ProductType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
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


    @Query("""
        {
            'productType': ?0,
        }
    """)
    public List<Product> getAllProductOfType(ProductType productType);

    // Usando Query Derivation ("nome do met0do")
    public List<Product> findByPriceLessThan(BigDecimal maxCost);



}
