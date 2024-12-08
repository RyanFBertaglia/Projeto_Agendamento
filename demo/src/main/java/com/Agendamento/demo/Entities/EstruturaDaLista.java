package com.Agendamento.demo.Entities;

import org.springframework.stereotype.Repository;

@Repository
public class EstruturaDaLista {
    String dia;
    String hora;

    public EstruturaDaLista(String dia, String hora) {
        this.dia = dia;
        this.hora = hora;
    }
}