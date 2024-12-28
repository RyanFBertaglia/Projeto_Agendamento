package com.Agendamento.demo.Model.Marcar;

import com.Agendamento.demo.exceptions.UserNaoEncontrado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class BuscarID {

    private final ConectaAoBancoDeDados conectaAoBancoDeDados;

    @Autowired
    public BuscarID(ConectaAoBancoDeDados conectaAoBancoDeDados) {
        this.conectaAoBancoDeDados = conectaAoBancoDeDados;
    }

    public int retornaId(String email, String senha) {

        String sql = "SELECT id from clientes\n" +
                "WHERE clientes.email = ? AND clientes.senha = ?";

        try (Connection conn = conectaAoBancoDeDados.Conexao();
             PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, email);
            stmt.setString(2, senha);

            ResultSet res = stmt.executeQuery();
            if (!res.next()) {
                throw new UserNaoEncontrado("Usuário não foi encontrado.");
            }

            return res.getInt(1);
        }catch (SQLException e) {
            throw new UserNaoEncontrado();
        }
    }
}
