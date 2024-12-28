package com.Agendamento.demo.Controller;

import com.Agendamento.demo.exceptions.*;
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
    private ResponseEntity<String> NaoCadastrar(HorarioIndisponivel exception){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(exception.getMessage());
    }

    @ExceptionHandler(UserNaoEncontrado.class)
    private ResponseEntity<String> userNaoEncontrado(UserNaoEncontrado exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(DataEnviadaErrada.class)
    private ResponseEntity<String> EnviouDataErrada(DataEnviadaErrada exception){
        return ResponseEntity.status(422).body(exception.getMessage());
    }
}
