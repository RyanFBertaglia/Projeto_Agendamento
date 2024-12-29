package com.Agendamento.demo.Model.Cadastro;

import com.Agendamento.demo.Model.Marcar.ConectaAoBancoDeDados;
import com.Agendamento.demo.exceptions.DataEnviadaErrada;
import com.Agendamento.demo.exceptions.HorarioIndisponivel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Service
public class Cadastro {
    private final ConectaAoBancoDeDados conectaAoBancoDeDados;

    @Autowired
    public Cadastro(ConectaAoBancoDeDados conectaAoBancoDeDados) {
        this.conectaAoBancoDeDados = conectaAoBancoDeDados;
    }

    public int cadastrar(String email, String senha) {

        String sql = "INSERT INTO clientes (email, senha)\n" +
                "VALUES (?, ?)";

        try (Connection conn = conectaAoBancoDeDados.Conexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, senha);

            return stmt.executeUpdate();
        } catch (SQLException e) {
            if ("23505".equals(e.getSQLState())) {
                throw new HorarioIndisponivel("Erro: Este usuario já existe");
            } else {
                System.out.println("Erro de conexão: " + e.getMessage());
            }
        }
        return 0;
    }
}
