package com.Agendamento.demo.Entities;

import com.Agendamento.demo.Model.BuscarID;
import lombok.Getter;

public class EstruturaDaInsercao extends EstruturaDaLista {

    @Getter
    private int id;
    private final String email;
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

}
