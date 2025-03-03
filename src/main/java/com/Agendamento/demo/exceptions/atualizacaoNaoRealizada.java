package com.Agendamento.demo.exceptions;

public class atualizacaoNaoRealizada extends RuntimeException {
    public atualizacaoNaoRealizada(String message) {
        super(message);
    }
}
