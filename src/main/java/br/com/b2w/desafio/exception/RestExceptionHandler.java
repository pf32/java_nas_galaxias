package br.com.b2w.desafio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PlanetaInexistenteException.class)
    public final ResponseEntity<?> handleTarefaException(PlanetaInexistenteException ex) {
        return new ResponseEntity<>(getError(ex, HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DadosInconsistentesException.class)
    public final ResponseEntity<?> handleDadosInconsistentesException(DadosInconsistentesException ex) {
        return new ResponseEntity<>(getError(ex, HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SWAPIException.class)
    public final ResponseEntity<?> handleSWAPIException(SWAPIException ex) {
        return new ResponseEntity<>(getError(ex, HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ErrorDetails getError(ApplicationException ex, HttpStatus status) {
        ErrorDetails errorDetail = new ErrorDetails();
        errorDetail.setStatus(status.value());
        errorDetail.setDetail(ex.getMessage());
        errorDetail.setDeveloperMessage(ex.getClass().getName());

        return errorDetail;
    }
}
