package com.Agendamento.demo.Model.UserService;

import com.Agendamento.demo.Entities.user.UserRole;
import com.Agendamento.demo.Model.HorariosService.AcessoDB;
import com.Agendamento.demo.exceptions.HorarioIndisponivel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Service
public class Cadastro {
    private final AcessoDB acessoDB;

    @Autowired
    public Cadastro(AcessoDB acessoDB) {
        this.acessoDB = acessoDB;
    }

    public void cadastrar(String email, String senha, UserRole role) {

        String sql = "INSERT INTO clientes (email, senha, role)\n" +
                "VALUES (?, ?, ?)";

        try (Connection conn = acessoDB.Conexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, senha);
            stmt.setString(3, String.valueOf(role));

            stmt.executeUpdate();
        } catch (SQLException e) {
            if ("23505".equals(e.getSQLState())) {
                throw new HorarioIndisponivel("Erro: Este usuario já existe");
            } else {
                System.out.println("Erro de conexão: " + e.getMessage());
            }
        }
    }
}
