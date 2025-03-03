package com.Agendamento.demo.Model.HorariosService;

import com.Agendamento.demo.Entities.EstruturaDoAgendamento;
import com.Agendamento.demo.Entities.EstruturaReagendamento;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class AtualizaHorarioTest {

    AtualizaHorario atualizaHorario;

    @Autowired
    AtualizaHorarioTest(AtualizaHorario atualizaHorario){
        this.atualizaHorario = atualizaHorario;
    }

    Map<String, EstruturaDoAgendamento> req = Map.of(
            "anterior", new EstruturaDoAgendamento("2025-07-25","10:30"),
            "atual", new EstruturaDoAgendamento("2025-10-08","14:30")
    );
    EstruturaReagendamento dados = new EstruturaReagendamento(req, 11);

    @Test
    void atualizarHorario() {
        assertDoesNotThrow(atualizaHorario.atualizarHorario(dados));
    }
}