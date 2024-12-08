package com.Agendamento.demo.Controller;

import com.Agendamento.demo.Entities.EstruturaDaLista;
import com.Agendamento.demo.Entities.Users;
import com.Agendamento.demo.Model.PesquisaNoBD;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class Controller {
    @PostMapping("/define")
    public ResponseEntity<String> recebe(@RequestBody Users user){
        String resposta = "Resposta por variavel";
       // ResponseEntity<User> entity = template. getForEntity("https:// example. com", String. class);
        return ResponseEntity.status(201).body(resposta);
    }

    @GetMapping("/buscahorarios")
    public ResponseEntity<EstruturaDaLista> BuscaHorario(@RequestBody String data){
        ArrayList<EstruturaDaLista> resposta = PesquisaNoBD.BuscaHorario(data);
        return ResponseEntity.status(201).body(resposta.getBody());
    }
}
