package ifmg.bkend._5.TrabalhoBackEnd_computer.resources.exceptions;

import ifmg.bkend._5.TrabalhoBackEnd_computer.services.exceptions.TypeConverterException;
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


}
