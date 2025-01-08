package com.Agendamento.demo.Model.UserService;

import com.Agendamento.demo.Entities.user.User;

public interface BuscaUser {
    int retornaId(String email) throws RuntimeException;
    User retornaUser(String userName);
}
