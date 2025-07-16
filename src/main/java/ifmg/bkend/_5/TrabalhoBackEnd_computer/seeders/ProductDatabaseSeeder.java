package ifmg.bkend._5.TrabalhoBackEnd_computer.seeders;

import ifmg.bkend._5.TrabalhoBackEnd_computer.models.Product;
import ifmg.bkend._5.TrabalhoBackEnd_computer.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class ProductDatabaseSeeder implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {

        List<Product> productslist = productRepository.findAll();

        if(productslist.isEmpty()){
            List<Product> products = List.of(
                    new Product("Intel Core i3-12100F", "Intel", "i3-12100F", new BigDecimal("599.90"), "cpu", 58, 25, 2, "Processador de entrada com bom custo-benefício para tarefas básicas."),
                    new Product("Intel Core i5-12400F", "Intel", "i5-12400F", new BigDecimal("849.90"), "cpu", 65, 30, 3, "Excelente desempenho para jogos e tarefas multitarefa."),
                    new Product("Intel Core i7-12700K", "Intel", "i7-12700K", new BigDecimal("1899.90"), "cpu", 125, 15, 5, "Processador de alto desempenho para gamers e criadores de conteúdo."),
                    new Product("Intel Core i9-13900K", "Intel", "i9-13900K", new BigDecimal("3499.90"), "cpu", 125, 10, 6, "Topo de linha da Intel para entusiastas e estações de trabalho."),
                    new Product("AMD Ryzen 5 5600", "AMD", "Ryzen 5 5600", new BigDecimal("749.90"), "cpu", 65, 28, 3, "Processador popular da AMD com ótimo custo-benefício."),
                    new Product("AMD Ryzen 7 5800X", "AMD", "Ryzen 7 5800X", new BigDecimal("1699.90"), "cpu", 105, 12, 5, "Ótimo para aplicações pesadas e gaming em alta performance."),
                    new Product("AMD Ryzen 9 7950X", "AMD", "Ryzen 9 7950X", new BigDecimal("3799.90"), "cpu", 170, 8, 6, "O mais poderoso da linha Ryzen, ideal para tarefas exigentes."),
                    new Product("NVIDIA GeForce GT 1030", "NVIDIA", "GT 1030", new BigDecimal("499.90"), "gpu", 30, 20, 0, "GPU básica ideal para vídeo e tarefas leves."),
                    new Product("AMD Radeon RX 6400", "AMD", "RX 6400", new BigDecimal("899.90"), "gpu", 53, 15, 1, "Boa opção de entrada com suporte a jogos leves."),
                    new Product("NVIDIA GeForce GTX 1650", "NVIDIA", "GTX 1650", new BigDecimal("1099.90"), "gpu", 75, 18, 2, "Placa de vídeo econômica para jogos em 1080p."),
                    new Product("AMD Radeon RX 6600", "AMD", "RX 6600", new BigDecimal("1499.90"), "gpu", 132, 12, 3, "Excelente custo-benefício para jogos em Full HD."),
                    new Product("NVIDIA GeForce RTX 3060 Ti", "NVIDIA", "RTX 3060 Ti", new BigDecimal("2399.90"), "gpu", 200, 10, 4, "GPU poderosa com suporte a Ray Tracing."),
                    new Product("AMD Radeon RX 7900 XT", "AMD", "RX 7900 XT", new BigDecimal("3999.90"), "gpu", 300, 6, 5, "Placa de alto desempenho para 1440p e 4K."),
                    new Product("NVIDIA GeForce RTX 4090", "NVIDIA", "RTX 4090", new BigDecimal("9499.90"), "gpu", 450, 3, 6, "Placa mais potente do mercado para entusiastas e criadores."),
                    new Product("Fonte C3Tech 500W Genérica", "C3Tech", "500W", new BigDecimal("159.90"), "psu", 500, 30, 0, "Fonte genérica básica, sem certificação."),
                    new Product("Fonte Bluecase 500W", "Bluecase", "BPC-H500", new BigDecimal("179.90"), "psu", 500, 25, 1, "Fonte básica com proteção contra curto, sem certificação."),
                    new Product("Fonte Corsair CV550 550W", "Corsair", "CV550", new BigDecimal("299.90"), "psu", 550, 20, 2, "Fonte com certificação 80 Plus Bronze."),
                    new Product("Fonte Cooler Master MWE 600W", "Cooler Master", "MWE 600", new BigDecimal("349.90"), "psu", 600, 18, 3, "Fonte eficiente para setups intermediários."),
                    new Product("Fonte Corsair RM750 Gold 750W", "Corsair", "RM750", new BigDecimal("649.90"), "psu", 750, 15, 4, "Fonte modular com certificação 80 Plus Gold."),
                    new Product("Fonte EVGA SuperNOVA G5 850W", "EVGA", "850 G5", new BigDecimal("899.90"), "psu", 850, 10, 5, "Fonte modular premium com certificação Gold."),
                    new Product("Fonte Seasonic PRIME TX-1000", "Seasonic", "PRIME TX-1000", new BigDecimal("1399.90"), "psu", 1000, 5, 6, "Fonte top de linha com certificação Titanium.")
            );
            productRepository.saveAll(products);
            System.out.println("Database of Products seeded.");
        }

    }




}
