package com.Agendamento.demo.Controller;

import com.Agendamento.demo.Entities.EstruturaDoAgendamento;
import com.Agendamento.demo.Entities.EstruturaDaLista;
import com.Agendamento.demo.Model.Marcar.PesquisaNoBD;
import com.Agendamento.demo.Model.Marcar.Servicos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@RestController
public class Controller {

    private final PesquisaNoBD pesquisaNoBD;
    private final Servicos servicos;

    @Autowired
    public Controller(PesquisaNoBD pesquisaNoBD, Servicos servicos) {
        this.pesquisaNoBD = pesquisaNoBD;
        this.servicos = servicos;
    }

    @GetMapping("/buscahorarios")
    public ResponseEntity<ArrayList<EstruturaDaLista>> BuscaHorario(@RequestParam String data){
        ArrayList<EstruturaDaLista> resposta = pesquisaNoBD.BuscaHorario(data);
        return ResponseEntity.status(200).body(resposta);
    }

    @PostMapping("/marcar")
    public ResponseEntity<String> marcarHorario(@RequestBody EstruturaDoAgendamento req){
        int id = servicos.setId(req.getEmail(), req.getSenha());
        int linhasAfet = servicos.salvarHorario(id, req.getDia(), req.getHora());
        return ResponseEntity.status(201).body("Agendado com sucesso, dia: " + req.getDia() + " hora: " + req.getHora() + " "+ linhasAfet);
    }

    @DeleteMapping("/cancelar")
    public ResponseEntity<String> cancelaHorario(@RequestBody EstruturaDoAgendamento req){
        int id = servicos.setId(req.getEmail(), req.getSenha());
        int linhasAfet = servicos.deletarHorario(id, req.getDia(), req.getHora());
        return ResponseEntity.status(200).body("Seu horaio foi cancelado");
    }
}
