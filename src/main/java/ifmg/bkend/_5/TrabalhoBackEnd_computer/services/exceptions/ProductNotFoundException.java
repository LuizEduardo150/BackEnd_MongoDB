package ifmg.bkend._5.TrabalhoBackEnd_computer.services.exceptions;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException() {
        super();
    }
    public ProductNotFoundException(String message) {
        super(message);
    }
}
