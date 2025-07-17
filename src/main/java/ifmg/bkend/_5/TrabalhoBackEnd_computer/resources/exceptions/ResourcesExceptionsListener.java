package ifmg.bkend._5.TrabalhoBackEnd_computer.resources.exceptions;

import ifmg.bkend._5.TrabalhoBackEnd_computer.services.exceptions.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ResourcesExceptionsListener {

    @ExceptionHandler(TypeConverterException.class)
    public ResponseEntity<StandartError> impossibleConversion(TypeConverterException ex, HttpServletRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandartError error = new StandartError();

        error.setStatus(status.value());
        error.setMessage(ex.getMessage());
        error.setError("Tipo de dados inconpativel: productType ou PerformanceLevel");
        error.setTimestamp(Instant.now());

        error.setPath(request.getRequestURI());

        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<StandartError> productNotFound(ProductNotFoundException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandartError error = new StandartError();

        error.setStatus(status.value());
        error.setMessage(ex.getMessage());
        error.setError("Produto informado na compra não cadastrado");
        error.setTimestamp(Instant.now());

        error.setPath(request.getRequestURI());

        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<StandartError> userNotFound(UserNotFoundException ex, HttpServletRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandartError error = new StandartError();

        error.setStatus(status.value());
        error.setMessage(ex.getMessage());
        error.setError("Usuário informado na compra, não cadastrado no sistema");
        error.setTimestamp(Instant.now());

        error.setPath(request.getRequestURI());

        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(NotEnoughStock.class)
    public ResponseEntity<StandartError> notEnoughStock(NotEnoughStock ex, HttpServletRequest request) {

        HttpStatus status = HttpStatus.CONFLICT;
        StandartError error = new StandartError();

        error.setStatus(status.value());
        error.setMessage(ex.getMessage());
        error.setError("Quantidade insuficiente no estoque");
        error.setTimestamp(Instant.now());

        error.setPath(request.getRequestURI());

        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(IncompatibleComputerParts.class)
    public ResponseEntity<StandartError> incompatibleParts(IncompatibleComputerParts ex, HttpServletRequest request) {

        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        StandartError error = new StandartError();

        error.setStatus(status.value());
        error.setMessage(ex.getMessage());
        error.setError("Peças para montagem não condizentes!");
        error.setTimestamp(Instant.now());

        error.setPath(request.getRequestURI());

        return ResponseEntity.status(status).body(error);
    }




}
