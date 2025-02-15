package com.Agendamento.demo.Model.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
public class AuthorizationService implements UserDetailsService {

    public final DadosUser dadosUser;

    @Autowired
    AuthorizationService(DadosUser dadosUser){
        this.dadosUser = dadosUser;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return dadosUser.retornaUser(email);
    }
}
