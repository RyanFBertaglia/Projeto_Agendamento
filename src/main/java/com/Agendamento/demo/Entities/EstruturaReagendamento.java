package com.Agendamento.demo.Entities;

import lombok.Getter;
import lombok.Setter;
import java.util.Map;

@Getter
@Setter
public class EstruturaReagendamento {
    private EstruturaDoAgendamento dataAnterior;
    private EstruturaDoAgendamento dataAtual;
    private int id;
    public EstruturaReagendamento(Map<String, EstruturaDoAgendamento> dados, int id) {
        this.dataAnterior = dados.get("anterior");
        this.dataAtual = dados.get("atual");
        this.id = id;
    }
}
