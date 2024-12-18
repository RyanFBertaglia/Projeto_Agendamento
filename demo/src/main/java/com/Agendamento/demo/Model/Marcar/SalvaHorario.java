package com.Agendamento.demo.Model.Marcar;

import com.Agendamento.demo.exceptions.DataEnviadaErrada;
import com.Agendamento.demo.exceptions.HorarioIndisponivel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class SalvaHorario {

    private final ConectaAoBancoDeDados conectaAoBancoDeDados;

    @Autowired
    public SalvaHorario(ConectaAoBancoDeDados conectaAoBancoDeDados) {
        this.conectaAoBancoDeDados = conectaAoBancoDeDados;
    }

    public int salvar(int id, String dia, String hora) {

        String sql = "INSERT INTO horarios_indisponiveis (dia, hora, idcliente)\n" +
                "VALUES (?::date, ?, ?)";

        try (Connection conn = conectaAoBancoDeDados.Conexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, dia);
            stmt.setString(2, hora);
            stmt.setInt(3, id);

           return stmt.executeUpdate();
        } catch (SQLException e) {
            if ("23505".equals(e.getSQLState())) {
                throw new HorarioIndisponivel("Erro: Horario já esta ocupado");
            } else if ("22008".equals(e.getSQLState())) {
                throw new DataEnviadaErrada();
            }
            else {
                System.out.println("Erro de conexão: " + e.getMessage());
            }
        }
        return 0;
    }
}
