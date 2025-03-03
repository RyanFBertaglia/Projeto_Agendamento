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


    @Autowired
    Servicos(SalvaHorario salvaHorario, DeletaHorario deletaHorario, PesquisaNoBD pesquisaNoBD, AtualizaHorario atualizaHorario){
        this.salvaHorario = salvaHorario;
        this.deletaHorario = deletaHorario;
        this.pesquisaNoBD = pesquisaNoBD;
        this.atualizaHorario = atualizaHorario;
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
    public ArrayList<EstruturaDaLista> BuscaHorario(String diaDeBusca) {
        return pesquisaNoBD.BuscaHorario(diaDeBusca);
    }
    @Override
    public boolean atualizaHorario(EstruturaReagendamento dados){return atualizaHorario.atualizarHorario(dados);}
}
