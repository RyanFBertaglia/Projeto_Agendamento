package com.Agendamento.demo.Entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;

@Repository
@Getter
@Setter
public class EstruturaDaLista {
    private String dia;
    private String hora;

    public EstruturaDaLista(String dia, String hora) {
        this.dia = dia;
        this.hora = hora;
    }
}