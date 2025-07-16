package ifmg.bkend._5.TrabalhoBackEnd_computer.resources;


import ifmg.bkend._5.TrabalhoBackEnd_computer.dtos.ProductDTO;
import ifmg.bkend._5.TrabalhoBackEnd_computer.models.Product;
import ifmg.bkend._5.TrabalhoBackEnd_computer.models.ProductType;
import ifmg.bkend._5.TrabalhoBackEnd_computer.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductResource {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<ProductDTO> getAllProducts(){
        /*
        List<Product> ret = productRepository.findAll();
        return ret.stream().map(ProductDTO::new).toList();

         */
        return null;
    }


    @GetMapping(value = "/sBrandMxCost/{brand}-{maxC}")
    public ResponseEntity<List<ProductDTO>> getAllProductOfBrandWithMaxCost(@PathVariable String brand, @PathVariable BigDecimal maxC){
        /*
        System.out.println(brand + maxC.toString());

        List<Product> products =  productRepository.getAllProductOfBrandWithMaxCost(brand, maxC);

        List<ProductDTO> dto = products.stream().map((p) -> new ProductDTO(p)).toList();

        return ResponseEntity.ok().body(dto);

         */
        return null;
    }

    @GetMapping(value = "/MxCost/{maxC}")
    public ResponseEntity<List<ProductDTO>> getAllProductWithMaxCost(@PathVariable BigDecimal maxC){
        /*
        List<Product> products =  productRepository.findByPriceLessThan(maxC);
        List<ProductDTO> dto = products.stream().map((p) -> new ProductDTO(p)).toList();
        return ResponseEntity.ok().body(dto);

         */
        return null;
    }



    @GetMapping(value = "/sTypeMxCost/{type}-{maxC}")
    public ResponseEntity<List<ProductDTO>> getAllProductOfTypeWithMaxCost(@PathVariable String type, @PathVariable BigDecimal maxC){
        /*
        List<Product> products =  productRepository.getAllProductOfTypeWithMaxCost(ProductType.fromValue(type), maxC);
        List<ProductDTO> dto = products.stream().map((p) -> new ProductDTO(p)).toList();
        return ResponseEntity.ok().body(dto);
         */
        return null;
    }



    @PostMapping
    public ResponseEntity<Void> insertProduct(@RequestBody ProductDTO dto){
        /*
        System.out.println("Op Insert");
        System.out.println(dto);

        productRepository.save(new Product(dto));

        return ResponseEntity.ok().build();

         */
        return null;
    }



}
