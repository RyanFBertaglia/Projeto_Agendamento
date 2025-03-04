package com.Agendamento.demo.Controller;

import com.Agendamento.demo.Entities.EstruturaDoAgendamento;
import com.Agendamento.demo.Entities.EstruturaDaLista;
import com.Agendamento.demo.Entities.EstruturaReagendamento;
import com.Agendamento.demo.Model.HorariosService.Servicos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Map;

@RestController
public class Controller {

    private final Servicos servicos;
    private final EstruturaDoAgendamento estruturaDoAgendamento;

    @Autowired
    public Controller(Servicos servicos, EstruturaDoAgendamento estruturaDoAgendamento) {
        this.servicos = servicos;
        this.estruturaDoAgendamento = estruturaDoAgendamento;
    }

    @GetMapping("/buscahorarios")
    public ResponseEntity<ArrayList<EstruturaDaLista>> BuscaHorario(@RequestParam String data){
        ArrayList<EstruturaDaLista> resposta = servicos.BuscaHorario(data);
        return ResponseEntity.status(200).body(resposta);
    }
    @GetMapping("/buscamarcados")
    public ResponseEntity<ArrayList<EstruturaDaLista>> buscaMarcados(){
        ArrayList<EstruturaDaLista> resposta = servicos.retornaHorariosUser(estruturaDoAgendamento.inicializa());
        return ResponseEntity.status(200).body(resposta);
    }

    @PostMapping("/marcar")
    public ResponseEntity<String> marcarHorario(@RequestBody EstruturaDoAgendamento req) throws RuntimeException{
        servicos.salvarHorario(estruturaDoAgendamento.inicializa(), req.getDia(), req.getHora());
        return ResponseEntity.status(201).body("Agendado com sucesso, dia: " + req.getDia() + " hora: " + req.getHora());
    }

    @DeleteMapping("/cancelar")
    public ResponseEntity<String> cancelaHorario(@RequestBody EstruturaDoAgendamento req){
        servicos.deletarHorario(estruturaDoAgendamento.inicializa(), req.getDia(), req.getHora());
        return ResponseEntity.status(200).body("Seu horaio foi cancelado");
    }

    @DeleteMapping("/cancelarTerceiros")
    public ResponseEntity<String> cancelaHorarioTerceiros(@RequestBody EstruturaDoAgendamento req){
        servicos.deletarHorario(req.getDia(), req.getHora());
        return ResponseEntity.status(200).body("Seu horaio foi cancelado");
    }

    @PutMapping("/atualizarHorario")
    public ResponseEntity<String> atualizaHorarios(@RequestBody Map<String, EstruturaDoAgendamento> dados){
        EstruturaReagendamento reagendamento = new EstruturaReagendamento(dados, estruturaDoAgendamento.inicializa());
        servicos.atualizaHorario(reagendamento);
        return ResponseEntity.status(200).body("Seu horaio foi atualizado");
    }
}
