package com.Agendamento.demo.Controller;

import com.Agendamento.demo.Entities.user.LoginResponseDTO;
import com.Agendamento.demo.Entities.user.User;
import com.Agendamento.demo.Entities.user.UserDto;
import com.Agendamento.demo.Entities.user.UserRole;
import com.Agendamento.demo.Model.UserService.Cadastro;
import com.Agendamento.demo.infra.TokenService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    private final Cadastro cadastro;
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthenticationController(Cadastro cadastro, AuthenticationManager authenticationManager, TokenService tokenService) {
        this.cadastro = cadastro;
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/cadUser")
    public ResponseEntity<String> cadastroUser(@RequestBody User cad){
        String senhaEncriptada = new BCryptPasswordEncoder().encode(cad.getSenha());
        this.cadastro.cadastrar(cad.getEmail(), senhaEncriptada, UserRole.USER);
        return ResponseEntity.status(201).body("Cadastrado com sucesso ");
    }

    /* Alterar modo de cadastro depois */
    @PostMapping("/cadAdmin")
    public ResponseEntity<String> cadastroAdm(@RequestBody User cad){
        String senhaEncriptada = new BCryptPasswordEncoder().encode(cad.getSenha());
        this.cadastro.cadastrar(cad.getEmail(), senhaEncriptada, UserRole.ADMIN);
        return ResponseEntity.status(201).body("Cadastrado com sucesso ");
    }


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserDto data, HttpServletResponse response){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());

        Cookie cookie = new Cookie("jwtToken", token);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(7 * 24 * 60 * 60);
        response.addCookie(cookie);
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }
}
