package ifmg.bkend._5.TrabalhoBackEnd_computer.services.exceptions;

public class NotEnoughStock extends RuntimeException {

    public NotEnoughStock() {
        super();
    }

    public NotEnoughStock(String message) {
        super(message);
    }
}
