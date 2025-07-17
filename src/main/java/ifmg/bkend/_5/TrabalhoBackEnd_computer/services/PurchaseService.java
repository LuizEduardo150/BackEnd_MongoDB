package ifmg.bkend._5.TrabalhoBackEnd_computer.services;

import ifmg.bkend._5.TrabalhoBackEnd_computer.dtos.PurchaseDTO;
import ifmg.bkend._5.TrabalhoBackEnd_computer.models.*;
import ifmg.bkend._5.TrabalhoBackEnd_computer.repository.ProductRepository;
import ifmg.bkend._5.TrabalhoBackEnd_computer.repository.PurchaseRepository;
import ifmg.bkend._5.TrabalhoBackEnd_computer.repository.UserRepository;
import ifmg.bkend._5.TrabalhoBackEnd_computer.services.exceptions.IncompatibleComputerParts;
import ifmg.bkend._5.TrabalhoBackEnd_computer.services.exceptions.NotEnoughStock;
import ifmg.bkend._5.TrabalhoBackEnd_computer.services.exceptions.ProductNotFoundException;
import ifmg.bkend._5.TrabalhoBackEnd_computer.services.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;


    public boolean makeApurchaseOrder(PurchaseDTO dto){

        User user =  userRepository.findByUserName(dto.getUserName());

        if(user == null)
            throw new UserNotFoundException("Usuário solicitante não está cadastrado no sistema.");

        System.out.println("Olha achei o user!");

        BigDecimal cont = BigDecimal.valueOf(0);
        List<BasicProductDescription> listproductsDesc = new ArrayList<>();

        for(String productId : dto.getProductsIds()){
            Optional<Product> optp = productRepository.findById(productId);
            if(optp.isEmpty())
                throw new ProductNotFoundException("Produto cadastrado na lista de compras não encontrado");

            Product product = optp.get();

            if(product.getQuantityInStock() == 0)
                throw new NotEnoughStock("produto: " + product.getName() + product.getModel());

            product.setQuantityInStock(product.getQuantityInStock() - 1);
            productRepository.save(product); // atualizar estoque

            cont = cont.add(product.getPrice());

            listproductsDesc.add(new BasicProductDescription(product));
        }

        // List<BasicProductDescription> idItems, BigDecimal totalCost, Date date, String clientName, String clientUserName, String clientEmail
        PurchaseOrder purchaseOrder = new PurchaseOrder(listproductsDesc, cont, LocalDate.now(), user.getNomeReal(), user.getUserName(), user.getEmail());

        purchaseRepository.save(purchaseOrder);

        return true;
    }


    public boolean makePcBuilderPurchaseOrder(PurchaseDTO dto){

        User user =  userRepository.findByUserName(dto.getUserName());

        if(user == null)
            throw new UserNotFoundException("Usuário solicitante não está cadastrado no sistema.");

        BigDecimal cont = BigDecimal.valueOf(0);
        List<BasicProductDescription> listproductsDesc = new ArrayList<>();

        int cpuamt = 0;
        int gpuamt = 0;
        int psuamt = 0;
        int totalTDP = 0;
        int psuTDP = -1;
        int cpuPower = 0;
        int gpuPower = 0;


        for(String productId : dto.getProductsIds()){
            Optional<Product> optp = productRepository.findById(productId);
            if(optp.isEmpty())
                throw new ProductNotFoundException("Produto cadastrado na lista de compras não encontrado");

            Product product = optp.get();

            // verificar estoque
            if(product.getQuantityInStock() == 0)
                throw new NotEnoughStock("produto: " + product.getName() + product.getModel());

            // Verificar tipo de peça comprada
            if(product.getProductType().equals(ProductType.CPU)){
                cpuamt++;
                totalTDP += product.getTdp();
                cpuPower = product.getPerformanceLevel().getValue();
            }
            else if(product.getProductType().equals(ProductType.GPU)){
                gpuamt++;
                totalTDP += product.getTdp();
                gpuPower = product.getPerformanceLevel().getValue();
            }
            else if(product.getProductType().equals(ProductType.PSU)){
                psuamt++;
                if(psuTDP < 0)
                    psuTDP = product.getTdp();
            }

            // veririfacr qtd de cada tipo de peça comprada
            if(cpuamt > 1)
                throw new IncompatibleComputerParts("Não é necessário mais de uma CPUs para montar um computador.");

            if(gpuamt > 1)
                throw new IncompatibleComputerParts("Não é necessário mais de uma GPUs para montar um computador.");

            if(psuamt > 1)
                throw new IncompatibleComputerParts("Não é necessário mais de uma Fonte para montar um computador.");


            // usuário está comprando fonte e ela é fraca
            if(psuTDP > 0 && psuamt < totalTDP)
                throw new IncompatibleComputerParts("Sua configuração ultrapassa consumo de sua fonte: " + psuTDP + "W para: " + totalTDP + "W");

            // Usuário comprando placa de video e processador
            if(cpuamt > 0 && gpuamt > 0){
                int dif = gpuPower - cpuPower;
                if(dif < 0)
                    dif *= -1;

                if(cpuPower < gpuPower &&  dif > 2)
                    throw new IncompatibleComputerParts("Sua CPU e GPU devem ter níveis condizentes de desempenho");
            }

            product.setQuantityInStock(product.getQuantityInStock() - 1);
            productRepository.save(product); // atualizar estoque

            cont = cont.add(product.getPrice());

            listproductsDesc.add(new BasicProductDescription(product));
        }

        // List<BasicProductDescription> idItems, BigDecimal totalCost, Date date, String clientName, String clientUserName, String clientEmail
        PurchaseOrder purchaseOrder = new PurchaseOrder(listproductsDesc, cont, LocalDate.now(), user.getNomeReal(), user.getUserName(), user.getEmail());

        purchaseRepository.save(purchaseOrder);

        return true;
    }









}
