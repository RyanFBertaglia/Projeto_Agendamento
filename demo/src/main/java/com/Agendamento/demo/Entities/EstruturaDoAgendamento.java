package com.Agendamento.demo.Entities;

import com.Agendamento.demo.Model.Marcar.BuscarID;
import com.Agendamento.demo.Model.Marcar.DeletaHorario;
import com.Agendamento.demo.Model.Marcar.SalvaHorario;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

public class EstruturaDoAgendamento extends EstruturaDaLista {

    @Getter
    private final int id;
    private final String email;
    private final String senha;

    private BuscarID buscarID;
    private SalvaHorario salvaHorario;
    private DeletaHorario deletaHorario;

    @Autowired
    public EstruturaDoAgendamento(String dia, String hora, String email, String senha) throws Exception{
        super(dia, hora);
        this.email = email;
        this.senha = senha;

        this.buscarID = buscarID;
        this.salvaHorario = salvaHorario;
        this.deletaHorario = deletaHorario;

        this.id = setId(email, senha);
    }

    public int setId(String email, String senha) throws Exception{
        int id = buscarID.retornaId(email, senha);
        if (id == 0) throw new RuntimeException("Erro ao validar o usuario");
        return id;
    }

    public int salvaHorario(){
        return salvaHorario.salvar(this.getId(), this.getDia(), this.getHora());
    }
    public int deletaHorario(){
        return deletaHorario.deletar(this.getId(), this.getDia(), this.getHora());
    }


}
