package com.Agendamento.demo.Model.UserService;

import com.Agendamento.demo.Entities.user.User;
import com.Agendamento.demo.Entities.user.UserRole;
import com.Agendamento.demo.Model.HorariosService.AcessoDB;
import com.Agendamento.demo.exceptions.UserNaoEncontrado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class DadosUser implements BuscaUser {

    private final AcessoDB acessoDB;

    @Autowired
    public DadosUser(AcessoDB acessoDB) {
        this.acessoDB = acessoDB;
    }

    public int retornaId(String email) throws RuntimeException{

        String sql = "SELECT id from clientes\n" +
                "WHERE clientes.email = ?";
        System.out.println(email);

        try (Connection conn = acessoDB.Conexao();
             PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, email);

            ResultSet res = stmt.executeQuery();
            if (!res.next()) {
                System.out.println("Erro de resposta");
                throw new UserNaoEncontrado("Usuário não foi encontrado.");
            }

            return res.getInt(1);
        }catch (SQLException e) {
            throw new UserNaoEncontrado();
        }
    }
    public User retornaUser(String userName){
        String sql = "SELECT id, email, senha, role from clientes\n" +
                "WHERE clientes.email = ?";

        try (Connection conn = acessoDB.Conexao();
             PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, userName);

            ResultSet res = stmt.executeQuery();
            if (!res.next()) {
                throw new UserNaoEncontrado("Usuário não foi encontrado.");
            }

            int id = res.getInt(1);
            String email = res.getString(2);
            String senha = res.getString(3);
            UserRole role = UserRole.valueOf(res.getString(4));

            return new User(id, email, senha, role);
        }catch (SQLException e) {
            throw new UserNaoEncontrado();
        }
    }
}
