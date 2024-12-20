package com.Agendamento.demo.Entities;

import java.lang.String;


public class EstruturaDaLista {
    private final String dia;
    private final String hora;

    public EstruturaDaLista(String dia, String hora) {
        this.dia = dia;
        this.hora = hora;
    }

    public String getDia() {
        return this.dia;
    }

    public String getHora() {
        return this.hora;
    }

}