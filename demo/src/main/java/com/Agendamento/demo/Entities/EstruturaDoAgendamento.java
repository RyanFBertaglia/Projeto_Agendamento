package com.Agendamento.demo.Entities;

import com.Agendamento.demo.Model.UserService.DadosUser;
import com.Agendamento.demo.exceptions.UserNaoEncontrado;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@Getter
public class EstruturaDoAgendamento {

    @Setter
    private int id;
    private final String hora;
    private final String dia;

    private DadosUser dadosUser;

    public EstruturaDoAgendamento(
            @JsonProperty("dia") String dia,
            @JsonProperty("hora") String hora) {
        this.dia = dia;
        this.hora = hora;
    }


    public EstruturaDoAgendamento() {
        this.dia = "";
        this.hora = "";
        this.id = 0;
    }

    public String setUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            return authentication.getName();
        }
        throw new UserNaoEncontrado();
    }

    public int inicializa() {
        String user = setUser();
        return dadosUser.retornaId(user);
    }

    @Autowired
    public void setDadosUser(DadosUser dadosUser) {
        this.dadosUser = dadosUser;
    }
}
