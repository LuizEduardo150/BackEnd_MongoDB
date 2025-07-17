package ifmg.bkend._5.TrabalhoBackEnd_computer.resources;

import ifmg.bkend._5.TrabalhoBackEnd_computer.dtos.PurchaseDTO;
import ifmg.bkend._5.TrabalhoBackEnd_computer.services.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/purchase")
public class PurchaseResource {


    @Autowired
    private PurchaseService purchaseService;


    @PostMapping
    public ResponseEntity<Void> makeAPurchaseOrder(@RequestBody PurchaseDTO dto){

        boolean ret = purchaseService.makeApurchaseOrder(dto);

        if (ret)
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


    @PostMapping(value = "/pcSmartBuilder")
    public ResponseEntity<Void> makeASmartPcBuilderPurchaseOrder(@RequestBody PurchaseDTO dto){

        boolean ret = purchaseService.makePcBuilderPurchaseOrder(dto);

        if (ret)
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }




}
