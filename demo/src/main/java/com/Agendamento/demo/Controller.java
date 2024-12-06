package com.Agendamento.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @PostMapping("/define")
    public ResponseEntity<String> recebe(@RequestBody Users user){
        String resposta = "Resposta por variavel";
       // ResponseEntity<User> entity = template. getForEntity("https:// example. com", String. class);
        return ResponseEntity.status(201).body(resposta);
    }
}
