package com.Agendamento.demo.Model.HorariosService;

import com.Agendamento.demo.exceptions.DataEnviadaErrada;
import com.Agendamento.demo.exceptions.HorarioIndisponivel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Service
public class SalvaHorario {

    private final AcessoDB acessoDB;

    @Autowired
    public SalvaHorario(AcessoDB acessoDB) {
        this.acessoDB = acessoDB;
    }

    public void salvar(int id, String dia, String hora) {

        String sql = "INSERT INTO horarios_indisponiveis (dia, hora, idcliente)\n" +
                "VALUES (?::date, ?, ?)";

        try (Connection conn = acessoDB.Conexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, dia);
            stmt.setString(2, hora);
            stmt.setInt(3, id);
            stmt.executeUpdate();
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
    }
}
