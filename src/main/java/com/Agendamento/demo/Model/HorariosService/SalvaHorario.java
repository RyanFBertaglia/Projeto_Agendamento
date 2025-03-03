package com.Agendamento.demo.Model.HorariosService;

import com.Agendamento.demo.exceptions.DataEnviadaErrada;
import com.Agendamento.demo.exceptions.HorarioIndisponivel;
import com.Agendamento.demo.exceptions.UserNaoEncontrado;
import com.Agendamento.demo.exceptions.atualizacaoNaoRealizada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class SalvaHorario {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SalvaHorario(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean salvar(int id, String dia, String hora) {

        String sql = "INSERT INTO horarios_indisponiveis (dia, hora, idcliente)\n" +
                "VALUES (?::date, ?, ?)";

        ConfereHorario.confereDia(dia);
        ConfereHorario.confereHora(hora);

        try {
            int v = jdbcTemplate.update(sql, dia, hora, id);
            if (v == 0) { throw new atualizacaoNaoRealizada("Erro ao salvar horário");}
        } catch (DataAccessException e) {
            if (id == 0) {
                throw new UserNaoEncontrado();
            }
            Throwable cause = e.getCause();

            if (cause instanceof SQLException sqlEx) {
                String sqlState = sqlEx.getSQLState();
                switch (sqlState) {
                    case "22008":
                        throw new DataEnviadaErrada();
                    case "23505":
                        throw new HorarioIndisponivel("Erro: Horário já está ocupado");
                }
                throw new RuntimeException("Erro de conexão: " + e.getMessage());
            }
        }
        return true;
    }
}