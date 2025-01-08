package com.Agendamento.demo.Model.HorariosService;
import com.Agendamento.demo.exceptions.FalhaConexao;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class AcessoDB {

        private static final String url = "jdbc:postgresql://ep-shiny-water-a6xpmjed.us-west-2.retooldb.com/retool?sslmode=require";
        private static final String user = "retool";
        private static final String password = "Ox3yXF2lsZdg";

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

