package com.Agendamento.demo.Model.HorariosService;

import java.sql.*;
import java.util.ArrayList;

import com.Agendamento.demo.Entities.EstruturaDaLista;
import com.Agendamento.demo.exceptions.DataEnviadaErrada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;


@Service
public class PesquisaNoBD {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PesquisaNoBD(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public ArrayList<EstruturaDaLista> BuscaHorario(String diaDeBusca) throws DataAccessException{
        String sql = "SELECT * FROM horarios_indisponiveis WHERE dia = ?::date";
        ConfereHorario.confereDia(diaDeBusca);

        try {
            return (ArrayList<EstruturaDaLista>) jdbcTemplate.query(sql, new Object[]{diaDeBusca}, this::mapper);
        }catch (DataAccessException e) {
            Throwable cause = e.getCause();

            if (cause instanceof SQLException sqlEx) {
                String sqlState = sqlEx.getSQLState();
                if ("22007".equals(sqlState) || "22018".equals(sqlState)) {
                    throw new DataEnviadaErrada();
                }
            }
            throw new RuntimeException("Erro de conexão: " + e.getMessage());
        }
    }

    private EstruturaDaLista mapper(ResultSet rs, int row) throws SQLException{
        return new EstruturaDaLista(
                rs.getString(1),
                rs.getString(2)
        );
    }
}