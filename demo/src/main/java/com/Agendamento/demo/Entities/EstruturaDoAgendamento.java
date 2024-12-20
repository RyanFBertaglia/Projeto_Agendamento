package com.Agendamento.demo.Entities;

import org.springframework.stereotype.Component;

@Component
public class EstruturaDoAgendamento {

    private final String email;
    private final String senha;
    private final String dia;
    private final String hora;

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


    public String getDia() {
        return dia;
    }

    public String getHora() {
        return hora;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }
    /*public int salvaHorario() {
        return servicos.salvarHorario(this.id, this.dia, this.hora);
    }

    public int deletaHorario() {
        return servicos.deletarHorario(this.id, this.dia, this.hora);
    }*/
}
