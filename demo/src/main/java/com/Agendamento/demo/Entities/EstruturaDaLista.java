package com.Agendamento.demo.Entities;

import lombok.Getter;

import java.lang.String;

@Getter
public class EstruturaDaLista {
    private final String dia;
    private final String hora;

    public EstruturaDaLista(String dia, String hora) {
        this.dia = dia;
        this.hora = hora;
    }
}