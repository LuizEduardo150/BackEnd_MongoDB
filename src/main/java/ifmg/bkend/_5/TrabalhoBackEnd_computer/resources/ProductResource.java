package ifmg.bkend._5.TrabalhoBackEnd_computer.resources;

import ifmg.bkend._5.TrabalhoBackEnd_computer.dtos.ProductDTO;
import ifmg.bkend._5.TrabalhoBackEnd_computer.models.Product;
import ifmg.bkend._5.TrabalhoBackEnd_computer.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;


@RestController
@RequestMapping("/product")
@Tag(name = "Produtos", description = "Gerenciamento de produtos")
public class ProductResource {

    @Autowired
    private ProductService productService;

    @Operation(summary = "Lista todos os produtos", description = "Retorna uma lista completa de todos os produtos cadastrados no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de produtos retornada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductDTO.class)))
    })
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts(){
        List<ProductDTO> ret = productService.getAllProducts();
        return ResponseEntity.ok().body(ret);
    }

    @Operation(summary = "Busca produtos por marca e custo máximo", description = "Retorna uma lista de produtos de uma marca específica com preço igual ou inferior ao custo máximo.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de produtos retornada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductDTO.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida (parâmetros mal formatados)")
    })
    @GetMapping(value = "/sBrandMxCost/{brand}-{maxC}")
    public ResponseEntity<List<ProductDTO>> getAllProductOfBrandWithMaxCost(
            @Parameter(description = "Marca do produto", example = "Intel")
            @PathVariable String brand,
            @Parameter(description = "Custo máximo (preço) do produto", example = "2500.00")
            @PathVariable BigDecimal maxC
    ){
        List<ProductDTO> products =  productService.getAllProductOfBrandWithMaxCost(brand, maxC);

        return ResponseEntity.ok().body(products);
    }

    @Operation(summary = "Busca produtos por custo máximo", description = "Retorna uma lista de produtos com preço igual ou inferior ao custo máximo.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de produtos retornada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductDTO.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida (parâmetro mal formatado)")
    })
    @GetMapping(value = "/MxCost/{maxC}")
    public ResponseEntity<List<ProductDTO>> getAllProductWithMaxCost(
            @Parameter(description = "Custo máximo (preço) do produto",
                    example = "1500.00")
            @PathVariable BigDecimal maxC
    ){
        List<ProductDTO> products =  productService.getAllProductWithMaxCost(maxC);
        return ResponseEntity.ok().body(products);
    }

    @Operation(summary = "Busca produtos por tipo", description = "Retorna uma lista de produtos de um tipo específico (ex: CPU, GPU, RAM).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de produtos retornada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductDTO.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida (parâmetro mal formatado)")
    })
    @GetMapping(value = "/psType/{type}")
    public ResponseEntity<List<ProductDTO>> getAllProductOfType(
            @Parameter(description = "Tipo do produto (ex: CPU, GPU, RAM, STORAGE)",
                    example = "CPU")
            @PathVariable String type
    ){
        List<ProductDTO> products =  productService.getAllProductOfType(type);

        return ResponseEntity.ok().body(products);
    }

    @Operation(summary = "Busca produtos por tipo e custo máximo", description = "Retorna uma lista de produtos de um tipo específico com preço igual ou inferior ao custo máximo.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de produtos retornada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductDTO.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida (parâmetros mal formatados)")
    })
    @GetMapping(value = "/sTypeMxCost/{type}-{maxC}")
    public ResponseEntity<List<ProductDTO>> getAllProductOfTypeWithMaxCost(
            @Parameter(description = "Tipo do produto (ex: CPU, GPU, RAM, STORAGE)", example = "GPU") @PathVariable String type,
            @Parameter(description = "Custo máximo (preço) do produto", example = "3000.00") @PathVariable BigDecimal maxC
    ){
        List<ProductDTO> products =  productService.getAllProductOfTypeWithMaxCost(type, maxC);

        return ResponseEntity.ok().body(products);
    }


    @Operation(summary = "Insere um novo produto", description = "Adiciona um novo produto ao estoque.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto inserido com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida (dados faltando ou mal formatados)"),
            @ApiResponse(responseCode = "409", description = "Conflito (produto já existe ou regra de negócio violada)")
    })
    @PostMapping
    public ResponseEntity<Void> insertProduct(@Valid @RequestBody ProductDTO dto){
        Boolean ret = productService.insertProduct(new Product(dto));

        if(ret)
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @Operation(summary = "Deleta todos os produtos", description = "**CUIDADO:** Remove todos os produtos do banco de dados. Esta operação é irreversível.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Todos os produtos deletados com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida ou erro na operação de exclusão")
    })
    @DeleteMapping(value = "/deleteAllDBSure")
    public ResponseEntity<Void> deleteAllDataBaseOfProducts(){
        boolean ret = productService.deleteAllDB();
        if (ret)
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.badRequest().build();
    }

    @Operation(summary = "Atualiza um produto por ID", description = "Atualiza os dados de um produto existente pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida (dados faltando ou mal formatados)"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado para atualização")
    })
    @PutMapping(value = "/upBid/{id}")
    public ResponseEntity<Void> updateProductById(@PathVariable String id, @RequestBody ProductDTO dto){
        boolean ret = productService.updateProductById(id, dto);
        if(ret)
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }


}
