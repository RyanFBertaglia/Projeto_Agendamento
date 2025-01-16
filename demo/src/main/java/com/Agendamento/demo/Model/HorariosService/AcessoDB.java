package com.Agendamento.demo.Model.HorariosService;
import com.Agendamento.demo.exceptions.FalhaConexao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class AcessoDB {

    @Value("${db.url}")
    private static String url;
    @Value("${db.user}")
    private static String user;
    @Value("${db.password}")
    private static String password;

    @Autowired
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

