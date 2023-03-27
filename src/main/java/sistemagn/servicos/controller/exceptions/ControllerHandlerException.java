package sistemagn.servicos.controller.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import sistemagn.servicos.service.exceptions.NotFoundException;
import sistemagn.servicos.service.exceptions.DataIntegrityViolationException;

import java.time.Instant;

@ControllerAdvice
public class ControllerHandlerException {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardError> notFoundException(NotFoundException notFoundException, HttpServletRequest request) {

        StandardError error = StandardError.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.NOT_FOUND.value())
                .Error("NOT FOUND EXCEPTION")
                .messenger(notFoundException.getMessage())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);

    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> notFoundException(DataIntegrityViolationException dataIntegrityViolationException, HttpServletRequest request) {

        StandardError error = StandardError.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.CONFLICT.value())
                .Error("DataIntegrityViolationException")
                .messenger(dataIntegrityViolationException.getMessage())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> validationError(MethodArgumentNotValidException m, HttpServletRequest request) {

        ValidationError error = new ValidationError(Instant.now(), HttpStatus.BAD_REQUEST.value(),
                "MethodArgumentNotValidException", "Erro Validaçao dos campos", request.getRequestURI());

        for (FieldError obj : m.getBindingResult().getFieldErrors()) {
            error.addErro(obj.getField(), obj.getDefaultMessage());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
