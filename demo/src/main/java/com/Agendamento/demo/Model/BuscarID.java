package com.Agendamento.demo.Model;

import com.Agendamento.demo.exceptions.UserNaoEncontrado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BuscarID {
    public static int retornaId(String email, String senha) {

        String sql = "SELECT id from clientes\n" +
                "WHERE clientes.email = ? AND clientes.senha = ?";

        try (Connection conn = ConectaAoBancoDeDados.Conexao();
             PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, email);
            stmt.setString(2, senha);

            ResultSet res = stmt.executeQuery();
            if (!res.next()) { // Verifica se o ResultSet está vazio
                throw new UserNaoEncontrado("Usuário não foi encontrado.");
            }

            return res.getInt(1);
        }catch (SQLException e) {
            throw new UserNaoEncontrado();
        }
    }
}
