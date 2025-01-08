package com.Agendamento.demo.Model.HorariosService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Servicos implements ServicosAgendamento{
    private final SalvaHorario salvaHorario;
    private final DeletaHorario deletaHorario;


    @Autowired
    Servicos(SalvaHorario salvaHorario, DeletaHorario deletaHorario){
        this.salvaHorario = salvaHorario;
        this.deletaHorario = deletaHorario;
    }

    public void salvarHorario(Integer id, String dia, String hora){
         salvaHorario.salvar(id, dia, hora);
    }
    public void deletarHorario(Integer id, String dia, String hora) {
         deletaHorario.deletar(id, dia, hora);
    }
}
