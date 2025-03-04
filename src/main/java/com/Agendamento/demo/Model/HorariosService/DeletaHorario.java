package com.Agendamento.demo.Model.HorariosService;

import com.Agendamento.demo.exceptions.DataEnviadaErrada;
import com.Agendamento.demo.exceptions.atualizacaoNaoRealizada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Service
public class DeletaHorario{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DeletaHorario(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void deletar(int id, String dia, String hora) {

        String sql = "DELETE FROM horarios_indisponiveis WHERE\n" +
                "dia = ?::date and hora = ? and idcliente = ?";

        try {
            int a = jdbcTemplate.update(sql, dia, hora, id);
            if(a == 0) {throw new atualizacaoNaoRealizada("Erro ao remover o horario");}
        } catch (DataAccessException e) {
            Throwable cause = e.getCause();

            if (cause instanceof SQLException sqlEx && sqlEx.getSQLState().equals("22008")) {
                throw new DataEnviadaErrada();
            }
            throw new RuntimeException("Erro de conexão: " + e.getMessage());
        }
    }
    public void deletar(String dia, String hora) {

        String sql = "DELETE FROM horarios_indisponiveis WHERE\n" +
                "dia = ?::date and hora = ?";

        try {
            int a = jdbcTemplate.update(sql, dia, hora);
            if(a == 0) {throw new atualizacaoNaoRealizada("Erro ao remover o horario");}
        } catch (DataAccessException e) {
            Throwable cause = e.getCause();

            if (cause instanceof SQLException sqlEx && sqlEx.getSQLState().equals("22008")) {
                throw new DataEnviadaErrada();
            }
            throw new RuntimeException("Erro de conexão: " + e.getMessage());
        }
    }
}
