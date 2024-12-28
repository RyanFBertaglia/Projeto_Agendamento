package com.Agendamento.demo.Entities;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class EstruturaDoAgendamento {

    private final String email;
    private final String senha;
    private final String hora;
    private final String dia;


    public EstruturaDoAgendamento(String dia, String hora, String email, String senha) {
        this.dia = dia;
        this.hora = hora;
        this.email = email;
        this.senha = senha;
    }

    public EstruturaDoAgendamento() {
        this.dia = "";
        this.hora = "";
        this.email = "";
        this.senha = "";
    }
}
