package com.Agendamento.demo.Model.HorariosService;

import com.Agendamento.demo.Entities.EstruturaDaLista;
import com.Agendamento.demo.Entities.EstruturaReagendamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Servicos implements ServicosAgendamento{
    private final SalvaHorario salvaHorario;
    private final DeletaHorario deletaHorario;
    private final PesquisaNoBD pesquisaNoBD;
    private final AtualizaHorario atualizaHorario;
    private final RetornarHorariosUser retornarHorariosUser;


    @Autowired
    Servicos(SalvaHorario salvaHorario, DeletaHorario deletaHorario, PesquisaNoBD pesquisaNoBD, AtualizaHorario atualizaHorario, RetornarHorariosUser retornarHorariosUser){
        this.salvaHorario = salvaHorario;
        this.deletaHorario = deletaHorario;
        this.pesquisaNoBD = pesquisaNoBD;
        this.atualizaHorario = atualizaHorario;
        this.retornarHorariosUser = retornarHorariosUser;
    }

    @Override
    public void salvarHorario(Integer id, String dia, String hora){
         salvaHorario.salvar(id, dia, hora);
    }
    @Override
    public void deletarHorario(Integer id, String dia, String hora) {
         deletaHorario.deletar(id, dia, hora);
    }
    @Override
    public void deletarHorario(String dia, String hora) { deletaHorario.deletar(dia, hora); }
    @Override
    public ArrayList<EstruturaDaLista> BuscaHorario(String diaDeBusca) {
        return pesquisaNoBD.BuscaHorario(diaDeBusca);
    }
    @Override
    public boolean atualizaHorario(EstruturaReagendamento dados){return atualizaHorario.atualizarHorario(dados);}
    @Override
    public ArrayList<EstruturaDaLista> retornaHorariosUser(int id) {
        return retornarHorariosUser.retornaHorariosUser(id);
    }
}
