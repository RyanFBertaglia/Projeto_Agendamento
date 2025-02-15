package com.Agendamento.demo.Model.HorariosService;
import com.Agendamento.demo.exceptions.FalhaConexao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class AcessoDB {

    @Value("${db.url}")
    private String url;
    @Value("${db.user}")
    private String user;
    @Value("${db.password}")
    private String password;

    private static Connection conn;

    public Connection Conexao() {

            try{
                if (conn == null || conn.isClosed()) {
                    conn = DriverManager.getConnection(url, user, password);
                }
                return conn;
            } catch (SQLException e) {
                throw new FalhaConexao();
            }
        }
    }

