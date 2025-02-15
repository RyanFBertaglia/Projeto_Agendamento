package com.Agendamento.demo.Model.HorariosService;

import com.Agendamento.demo.exceptions.DataEnviadaErrada;
import com.Agendamento.demo.exceptions.HorarioIndisponivel;
import com.Agendamento.demo.exceptions.UserNaoEncontrado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Service
public class SalvaHorario {

    private final AcessoDB acessoDB;

    @Autowired
    public SalvaHorario(AcessoDB acessoDB) {
        this.acessoDB = acessoDB;
    }

    //Voltar a ser void depois
    public boolean salvar(int id, String dia, String hora) {

        String sql = "INSERT INTO horarios_indisponiveis (dia, hora, idcliente)\n" +
                "VALUES (?::date, ?, ?)";

        confereDia(dia);
        confereHora(hora);

        try (Connection conn = acessoDB.Conexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, dia);
            stmt.setString(2, hora);
            stmt.setInt(3, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            if (id == 0){
                throw new UserNaoEncontrado();
            } else if ("23505".equals(e.getSQLState())) {
                throw new HorarioIndisponivel("Erro: Horario já esta ocupado");
            } else if ("22008".equals(e.getSQLState())) {
                throw new DataEnviadaErrada(e.getMessage());
            } else {
                throw new RuntimeException("Erro de conexão: " + e.getMessage());
            }
        }
    }

    public static void confereHora(String hora) {
        try {
            LocalTime.parse(hora, DateTimeFormatter.ofPattern("HH:mm"));
        } catch (DateTimeParseException e) {
            throw new DataEnviadaErrada(hora + " Não é uma hora valida");
        }
    }

    public static void confereDia(String dia){
        try {
            LocalDate.parse(dia, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            throw new DataEnviadaErrada(dia + " Não é um dia valido");
        }
    }
}
