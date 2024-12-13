package com.Agendamento.demo.Model;

import com.Agendamento.demo.exceptions.DataEnviadaErrada;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeletaHorario{
    public static int deletar(int id, String dia, String hora) {

        String sql = "DELETE FROM horarios_indisponiveis WHERE\n" +
                "dia = ?::date and hora = ? and idcliente = ?";

        try (Connection conn = ConectaAoBancoDeDados.Conexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, dia);
            stmt.setString(2, hora);
            stmt.setInt(3, id);

            return stmt.executeUpdate();
        } catch (SQLException e) {
            if ("22008".equals(e.getSQLState())) {
                throw new DataEnviadaErrada();
            }
            System.out.println("Erro de conexão: " + e.getMessage());
        }
        return 0;
    }
}
