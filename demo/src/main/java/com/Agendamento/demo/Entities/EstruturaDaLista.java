package com.Agendamento.demo.Entities;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.lang.String;


@Getter
@Component
public class EstruturaDaLista {
    private final String dia;
    private final String hora;

    public EstruturaDaLista(String dia, String hora) {
        this.dia = dia;
        this.hora = hora;
    }
    public EstruturaDaLista() {
        this.dia = "";
        this.hora = "";
    }
}