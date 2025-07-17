package ifmg.bkend._5.TrabalhoBackEnd_computer.services.exceptions;

public class IncompatibleComputerParts extends RuntimeException {
    public IncompatibleComputerParts() {
        super();
    }
    public IncompatibleComputerParts(String message) {
        super(message);
    }
}
