package com.Agendamento.demo.Model;

import com.Agendamento.demo.Entities.EstruturaDaLista;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BuscarID {
    public static int retornaId(String email, String senha){

        String sql = "SELECT id from clientes\n" +
                "WHERE clientes.email = ? AND clientes.senha = ?";

        String emailu = "ryanfernandes@gmail.com";
        String senhau = "ryanfe67";


        try (Connection conn = ConectaAoBancoDeDados.Conexao();
             PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, email);
            stmt.setString(2, senha);

            ResultSet res = stmt.executeQuery();
            res.next();
            return res.getInt(1);
        }catch (SQLException e) {
            System.out.println("Erro de conexão: " + e.getMessage());
            return 0;
        }
    }
}
