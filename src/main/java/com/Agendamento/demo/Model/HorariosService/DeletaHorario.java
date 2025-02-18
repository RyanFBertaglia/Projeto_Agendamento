package com.Agendamento.demo.Model.HorariosService;

import com.Agendamento.demo.exceptions.DataEnviadaErrada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Service
public class DeletaHorario{

    private final AcessoDB acessoDB;

    @Autowired
    public DeletaHorario(AcessoDB acessoDB) {
        this.acessoDB = acessoDB;
    }

    public void deletar(int id, String dia, String hora) {

        String sql = "DELETE FROM horarios_indisponiveis WHERE\n" +
                "dia = ?::date and hora = ? and idcliente = ?";

        try (Connection conn = acessoDB.Conexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, dia);
            stmt.setString(2, hora);
            stmt.setInt(3, id);

            stmt.executeUpdate();
        } catch (SQLException e) {
            if ("22008".equals(e.getSQLState())) {
                throw new DataEnviadaErrada();
            }
            throw new RuntimeException("Erro de conexão: " + e.getMessage());
        }
    }
}
