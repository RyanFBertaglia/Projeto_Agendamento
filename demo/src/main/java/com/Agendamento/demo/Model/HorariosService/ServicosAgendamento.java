package com.Agendamento.demo.Model.HorariosService;

public interface ServicosAgendamento {
    void salvarHorario(Integer id, String dia, String hora);
    void deletarHorario(Integer id, String dia, String hora);
}
