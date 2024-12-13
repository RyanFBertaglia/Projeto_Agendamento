package infra;

import exceptions.HorarioIndisponivel;
import exceptions.UserNaoEncontrado;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@ControllerAdvice
@ComponentScan
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(HorarioIndisponivel.class)
    private ResponseEntity<String> CredenciaisIncorretas(HorarioIndisponivel exception){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais incorretas");
    }

    @ExceptionHandler(UserNaoEncontrado.class)
    private ResponseEntity<String> userNaoEncontrado(UserNaoEncontrado exception){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(exception.getMessage());
    }
}
