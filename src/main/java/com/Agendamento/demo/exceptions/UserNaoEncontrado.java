package com.Agendamento.demo.exceptions;

public class UserNaoEncontrado extends RuntimeException{
            public UserNaoEncontrado() {super("Email ou senha incorretos"); }
            public UserNaoEncontrado(String mensagem) {super(mensagem); }
}
