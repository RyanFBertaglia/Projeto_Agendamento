package com.Agendamento.demo.Model.HorariosService;

import com.Agendamento.demo.Entities.EstruturaDaLista;
import com.Agendamento.demo.exceptions.DataEnviadaErrada;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Service
public class RetornarHorariosUser {
    private final JdbcTemplate jdbcTemplate;

    RetornarHorariosUser(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }


    public ArrayList<EstruturaDaLista> retornaHorariosUser(int id) {
        String sql = "SELECT * FROM horarios_indisponiveis WHERE idcliente = ?";

        try {
            return (ArrayList<EstruturaDaLista>) jdbcTemplate.query(sql, this::mapper, id);
        }catch (DataAccessException e) {
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
