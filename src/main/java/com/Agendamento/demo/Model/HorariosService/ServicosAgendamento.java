package com.Agendamento.demo.Model.HorariosService;

import com.Agendamento.demo.Entities.EstruturaDaLista;
import com.Agendamento.demo.Entities.EstruturaReagendamento;

import java.util.ArrayList;

public interface ServicosAgendamento {
    void salvarHorario(Integer id, String dia, String hora);
    void deletarHorario(Integer id, String dia, String hora);
    ArrayList<EstruturaDaLista> BuscaHorario(String diaDeBusca);
    boolean atualizaHorario(EstruturaReagendamento dados);
}
