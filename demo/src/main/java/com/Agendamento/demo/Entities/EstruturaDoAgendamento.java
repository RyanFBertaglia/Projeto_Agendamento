package com.Agendamento.demo.Entities;

import com.Agendamento.demo.Model.BuscarID;
import com.Agendamento.demo.Model.DeletaHorario;
import com.Agendamento.demo.Model.SalvaHorario;

public class EstruturaDoAgendamento extends EstruturaDaLista {

    private final int id;
    private final String email;
    private final String senha;

    public EstruturaDoAgendamento(String dia, String hora, String email, String senha) throws Exception{
        super(dia, hora);
        this.email = email;
        this.senha = senha;
        this.id = setId(email, senha);
    }

    public int setId(String email, String senha) throws Exception{
        int id = BuscarID.retornaId(email, senha);
        if (id == 0) throw new RuntimeException("Erro ao validar o usuario");
        return id;
    }

    public int getId() {
        return id;
    }

    public int salvaHorario(){
        return SalvaHorario.salvar(this.getId(), this.getDia(), this.getHora());
    }
    public int deletaHorario(){
        return DeletaHorario.deletar(this.getId(), this.getDia(), this.getHora());
    }


}
