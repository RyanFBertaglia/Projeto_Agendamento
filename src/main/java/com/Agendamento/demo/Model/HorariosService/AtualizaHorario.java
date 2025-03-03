package com.Agendamento.demo.Model.HorariosService;

import com.Agendamento.demo.Entities.EstruturaDoAgendamento;
import com.Agendamento.demo.Entities.EstruturaReagendamento;
import com.Agendamento.demo.exceptions.DataEnviadaErrada;
import com.Agendamento.demo.exceptions.HorarioIndisponivel;
import com.Agendamento.demo.exceptions.atualizacaoNaoRealizada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@Service
public class AtualizaHorario {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AtualizaHorario(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional(rollbackFor = {Exception.class})
    boolean atualizarHorario(EstruturaReagendamento dados) {

        EstruturaDoAgendamento antiga = dados.getDataAnterior();
        EstruturaDoAgendamento nova = dados.getDataAtual();

        String sqlDeleta = "DELETE FROM horarios_indisponiveis WHERE\n" +
                "dia = ?::date and hora = ? and idcliente = ?";
        String sqlSalva  = "INSERT INTO horarios_indisponiveis (dia, hora, idcliente)\n" +
                "VALUES (?::date, ?, ?)";
        ConfereHorario.confereDatas(antiga);
        try {
            int a = jdbcTemplate.update(sqlDeleta, antiga.getDia(), antiga.getHora(), dados.getId());
            int b = jdbcTemplate.update(sqlSalva, nova.getDia(), nova.getHora(), dados.getId());
            if(a!=1 || b != 1){throw new atualizacaoNaoRealizada("Erro ao atualizar o horário");}
            return true;
        }
        catch (DataAccessException e) {
            Throwable cause = e.getCause();

            if (cause instanceof SQLException sqlEx) {
                filtraExcecao(sqlEx.getSQLState());
            }
            throw new RuntimeException("Erro de conexão: " + e.getMessage());
        }
    }

        public void filtraExcecao(String sqlState) {
            switch (sqlState) {
                case "22008":
                    throw new DataEnviadaErrada();
                case "23505":
                    throw new HorarioIndisponivel("Erro: Horário já está ocupado");
                default:
                    throw new RuntimeException("Erro em realizar a operação. SQLState: " + sqlState);
            }
        }
}
