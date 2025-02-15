package com.Agendamento.demo.exceptions;

public class FalhaConexao extends RuntimeException {
        public FalhaConexao() {super("Falha ao conectar com o Banco de Dados"); }
}
