package com.Agendamento.demo.Model.HorariosService;

import com.Agendamento.demo.Entities.EstruturaDoAgendamento;
import com.Agendamento.demo.Entities.EstruturaReagendamento;
import com.Agendamento.demo.exceptions.DataEnviadaErrada;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ConfereHorario {
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

    public static void confereDatas(EstruturaDoAgendamento dados) throws RuntimeException{
        confereHora(dados.getHora());
        confereDia(dados.getDia());
    }
}
