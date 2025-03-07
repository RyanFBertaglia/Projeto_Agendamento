package com.Agendamento.demo.Model.HorariosService;

import com.Agendamento.demo.Entities.EstruturaDoAgendamento;
import com.Agendamento.demo.Entities.EstruturaReagendamento;
import com.Agendamento.demo.exceptions.DataEnviadaErrada;
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
    Map<String, EstruturaDoAgendamento> reqDiaAnterior = Map.of(
            "anterior", new EstruturaDoAgendamento("2025-07-0","10:30"),
            "atual", new EstruturaDoAgendamento("2025-10-30","14:30")
    );
    Map<String, EstruturaDoAgendamento> reqDiaAtual = Map.of(
            "anterior", new EstruturaDoAgendamento("2025-07-02","10:30"),
            "atual", new EstruturaDoAgendamento("2025-10-31","14:30")
    );
    Map<String, EstruturaDoAgendamento> reqHoraAtual = Map.of(
            "anterior", new EstruturaDoAgendamento("2025-07-02","20:30"),
            "atual", new EstruturaDoAgendamento("2025-10-30","25:30")
    );
    Map<String, EstruturaDoAgendamento> reqHoraAntiga = Map.of(
            "anterior", new EstruturaDoAgendamento("2025-07-02","25:30"),
            "atual", new EstruturaDoAgendamento("2025-10-30","14:30")
    );
    EstruturaReagendamento dados = new EstruturaReagendamento(req, 11);
    EstruturaReagendamento diaErrado = new EstruturaReagendamento(reqDiaAtual, 11);
    EstruturaReagendamento diaErrado2 = new EstruturaReagendamento(reqDiaAnterior, 11);
    EstruturaReagendamento horaErrada = new EstruturaReagendamento(reqHoraAtual, 11);
    EstruturaReagendamento horaErada2 = new EstruturaReagendamento(reqHoraAntiga, 11);


    @Test
    void atualizarHorario() {
        assertTrue(atualizaHorario.atualizarHorario(dados));
    }
    @Test
    void retornaErroDiaAt() {
        assertFalse(atualizaHorario.atualizarHorario(diaErrado));
    }
    @Test
    void retornaErroDiaAn() {
        assertFalse(atualizaHorario.atualizarHorario(diaErrado2));
    }
    @Test
    void retornaErroHoraAn() {
        assertFalse(atualizaHorario.atualizarHorario(horaErrada));
    }
    @Test
    void retornaErroHoraAt() {
        assertFalse(atualizaHorario.atualizarHorario(horaErada2));
    }
}