package com.Agendamento.demo.Entities;

import com.Agendamento.demo.Model.BuscarID;
import com.Agendamento.demo.Model.SalvaHorario;
import lombok.Getter;

public class EstruturaDaInsercao extends EstruturaDaLista {

    @Getter
    private int id;
    @Getter
    private final String email;
    @Getter
    private final String senha;

    public EstruturaDaInsercao(String dia, String hora, String email, String senha) {
        super(dia, hora);
        this.email = email;
        this.senha = senha;
        this.id = setId(email, senha);
    }

    public int setId(String email, String senha){
        return BuscarID.retornaId(email, senha);
    }

    public int salvaHorario(){
        return SalvaHorario.salvar(this.getId(), this.getDia(), this.getHora(), 1);
    }
    public int deletaHorario(){
        return SalvaHorario.salvar(this.getId(), this.getDia(), this.getHora(), 2);
    }
}
