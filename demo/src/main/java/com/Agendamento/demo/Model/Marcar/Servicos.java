package com.Agendamento.demo.Model.Marcar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Servicos {
    private final BuscarID buscarID;
    private final SalvaHorario salvaHorario;
    private final DeletaHorario deletaHorario;


    @Autowired
    Servicos(BuscarID buscarID, SalvaHorario salvaHorario, DeletaHorario deletaHorario){
        this.buscarID = buscarID;
        this.salvaHorario = salvaHorario;
        this.deletaHorario = deletaHorario;
    }


    public int salvarHorario(Integer id, String dia, String hora){
        return salvaHorario.salvar(id, dia, hora);
    }

    public int deletarHorario(Integer id, String dia, String hora) {
        return deletaHorario.deletar(id, dia, hora);
    }

    public int setId(String email, String senha){
        int id = buscarID.retornaId(email, senha);
        if (id == 0) throw new RuntimeException("Erro ao validar o usuario");
        return id;
    }
}
