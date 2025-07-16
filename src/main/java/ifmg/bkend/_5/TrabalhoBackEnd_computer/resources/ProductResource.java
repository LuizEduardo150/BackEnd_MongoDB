package ifmg.bkend._5.TrabalhoBackEnd_computer.resources;

import ifmg.bkend._5.TrabalhoBackEnd_computer.dtos.ProductDTO;
import ifmg.bkend._5.TrabalhoBackEnd_computer.models.Product;
import ifmg.bkend._5.TrabalhoBackEnd_computer.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<List<ProductDTO>> getAllProducts(){
        List<ProductDTO> ret = productService.getAllProducts();
        return ResponseEntity.ok().body(ret);
    }


    @GetMapping(value = "/sBrandMxCost/{brand}-{maxC}")
    public ResponseEntity<List<ProductDTO>> getAllProductOfBrandWithMaxCost(@PathVariable String brand, @PathVariable BigDecimal maxC){
        List<ProductDTO> products =  productService.getAllProductOfBrandWithMaxCost(brand, maxC);

        return ResponseEntity.ok().body(products);
    }

    @GetMapping(value = "/MxCost/{maxC}")
    public ResponseEntity<List<ProductDTO>> getAllProductWithMaxCost(@PathVariable BigDecimal maxC){
        List<ProductDTO> products =  productService.getAllProductWithMaxCost(maxC);
        return ResponseEntity.ok().body(products);
    }

    @GetMapping(value = "/psType/{type}")
    public ResponseEntity<List<ProductDTO>> getAllProductOfType(@PathVariable String type){
        List<ProductDTO> products =  productService.getAllProductOfType(type);

        return ResponseEntity.ok().body(products);
    }

    @GetMapping(value = "/sTypeMxCost/{type}-{maxC}")
    public ResponseEntity<List<ProductDTO>> getAllProductOfTypeWithMaxCost(@PathVariable String type, @PathVariable BigDecimal maxC){
        List<ProductDTO> products =  productService.getAllProductOfTypeWithMaxCost(type, maxC);

        return ResponseEntity.ok().body(products);
    }


    @PostMapping
    public ResponseEntity<Void> insertProduct(@Valid @RequestBody ProductDTO dto){
        Boolean ret = productService.insertProduct(new Product(dto));

        if(ret)
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }


    @DeleteMapping(value = "/deleteAllDBSure")
    public ResponseEntity<Void> deleteAllDataBaseOfProducts(){
        boolean ret = productService.deleteAllDB();
        if (ret)
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.badRequest().build();
    }

    @PutMapping(value = "/upBid/{id}")
    public ResponseEntity<Void> updateProductById(@PathVariable String id, @RequestBody ProductDTO dto){
        boolean ret = productService.updateProductById(id, dto);
        if(ret)
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }


}
