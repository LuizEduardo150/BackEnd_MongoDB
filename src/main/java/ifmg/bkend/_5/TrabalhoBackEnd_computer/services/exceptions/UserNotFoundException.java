package ifmg.bkend._5.TrabalhoBackEnd_computer.services.exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() { super();}
    public UserNotFoundException(String message) {
        super(message);
    }
}
