package ifmg.bkend._5.TrabalhoBackEnd_computer.services;

import ifmg.bkend._5.TrabalhoBackEnd_computer.dtos.ProductDTO;
import ifmg.bkend._5.TrabalhoBackEnd_computer.models.Product;
import ifmg.bkend._5.TrabalhoBackEnd_computer.models.ProductType;
import ifmg.bkend._5.TrabalhoBackEnd_computer.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;


    @Transactional(readOnly = true)
    public List<ProductDTO> getAllProducts(){
        List<Product> ret = productRepository.findAll();
        return ret.stream().map(ProductDTO::new).toList();
    }


    @Transactional(readOnly = true)
    public List<ProductDTO> getAllProductOfBrandWithMaxCost(String brand, BigDecimal maxC){
        List<Product> products =  productRepository.getAllProductOfBrandWithMaxCost(brand, maxC);
        return products.stream().map(ProductDTO::new).toList();
    }


    @Transactional(readOnly = true)
    public List<ProductDTO> getAllProductWithMaxCost(BigDecimal maxC){
        List<Product> products =  productRepository.findByPriceLessThan(maxC);
        return products.stream().map(ProductDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public List<ProductDTO> getAllProductOfType(String type){
        List<Product> products =  productRepository.getAllProductOfType(ProductType.fromValue(type));
        return products.stream().map(ProductDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public List<ProductDTO> getAllProductOfTypeWithMaxCost(String type, BigDecimal maxC){
        List<Product> products =  productRepository.getAllProductOfTypeWithMaxCost(ProductType.fromValue(type), maxC);
        return products.stream().map(ProductDTO::new).toList();
    }


    @Transactional
    public Boolean insertProduct(Product product){
        productRepository.save(product);
        return true;
    }

    public boolean updateProductById(String id, ProductDTO dto){
        if(!productRepository.existsById(id))
            return false;

        Optional<Product> opt = productRepository.findById(id);

        if(opt.isEmpty())
            return false;

        Product product = opt.get();

        if(dto.getTdp() != null)
            product.setTdp(dto.getTdp());

        if(dto.getDescription() != null)
            product.setDescription(dto.getDescription());

        if(dto.getProductType() != null){
            product.setProductType(dto.getProductType());
        }

        if(dto.getPerformanceLevel() != null)
            product.setPerformanceLevel(dto.getPerformanceLevel());

        if(dto.getBrand() != null)
            product.setBrand(dto.getBrand());

        if(dto.getModel() != null)
            product.setModel(dto.getModel());

        if(dto.getPrice() != null)
            product.setPrice(dto.getPrice());

        if (dto.getQuantityInStock() != null)
            product.setQuantityInStock(dto.getQuantityInStock());

        if (dto.getName() != null)
            product.setName(dto.getName());

        productRepository.save(product);

        return true;
    }

    @Transactional
    public Boolean deleteAllDB(){
        try {
            productRepository.deleteAll();
        }catch (Exception e){
            return false;
        }

        return true;
    }


}
