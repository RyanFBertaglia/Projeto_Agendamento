package com.Agendamento.demo.Controller;

import com.Agendamento.demo.Entities.EstruturaDaInsercao;
import com.Agendamento.demo.Entities.EstruturaDaLista;
import com.Agendamento.demo.Entities.Users;
import com.Agendamento.demo.Model.PesquisaNoBD;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Map;

@RestController
public class Controller {

    @PostMapping("/define")
    public ResponseEntity<String> recebe(@RequestBody Users user){
        String resposta = "Resposta por variavel";
        return ResponseEntity.status(201).body(resposta);
    }

    @GetMapping("/buscahorarios")
    public ResponseEntity<ArrayList<EstruturaDaLista>> BuscaHorario(@RequestParam String data){
        ArrayList<EstruturaDaLista> resposta = PesquisaNoBD.BuscaHorario(data);
        return ResponseEntity.status(200).body(resposta);
    }

    @PostMapping("/marcar")
    public int marcarHorario(@RequestBody EstruturaDaInsercao req){
        int id = req.getId();
        System.out.printf("%d", id);

        return id;
    }
}
