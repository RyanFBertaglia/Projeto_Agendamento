package com.Agendamento.demo.exceptions;

public class DataEnviadaErrada extends RuntimeException {
        public DataEnviadaErrada(String message) {super(message); }
        public DataEnviadaErrada() {super("A data enviada esta errada"); }
}
