package com.Agendamento.demo.Model.HorariosService;

import com.Agendamento.demo.exceptions.DataEnviadaErrada;
import com.Agendamento.demo.exceptions.UserNaoEncontrado;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SalvaHorarioTest {

    @Autowired
    SalvaHorario salvaHorario;

    @Test
    void Limites() {
        assertTrue(salvaHorario.salvar(7, "2025/03/25", "23:59"));
        assertThrows(DataEnviadaErrada.class, () -> {
            salvaHorario.salvar(1, "2025/03/32", "00:00");
        });
        assertTrue(salvaHorario.salvar(3, "2025/12/31", "00:01"));
        assertThrows(DataEnviadaErrada.class, () -> {
            salvaHorario.salvar(14, "2025/00/27", "24:01");
        });
        assertTrue(salvaHorario.salvar(1, "2025/03/30", "13:00"));
        assertThrows(UserNaoEncontrado.class, () -> {
            salvaHorario.salvar(0, "2025/03/17", "24:00");
        });
        assertThrows(DataEnviadaErrada.class, () -> {
            salvaHorario.salvar(15, "2025/03/32", "24:01");
        });

    }

    @Test
    void formatoData(){
        assertThrows(DataEnviadaErrada.class, () -> {
            salvaHorario.salvar(1, "10/03/2025", "23:00");
        });
        assertThrows(DataEnviadaErrada.class, () -> {
            salvaHorario.salvar(1, "2025/03/0", "24:00");
        });
        assertThrows(DataEnviadaErrada.class, () -> {
            salvaHorario.salvar(1, "2025/033/24", "12:00");
        });
        assertThrows(DataEnviadaErrada.class, () -> {
            salvaHorario.salvar(1, "2g25/03/15", "19:000");
        });
        assertTrue(salvaHorario.salvar(1, "2025/03/30", "14:00"));
        assertThrows(DataEnviadaErrada.class, () -> {
            salvaHorario.salvar(1, "2025/0/31", "14:53");
        });
    }
}