package com.Agendamento.demo;
import lombok.Getter;

import java.sql.*;
import java.util.ArrayList;

import static java.sql.DriverManager.getConnection;

public class ConectaAoBancoDeDados {

    public static void main(String[] args) {
        String url = "jdbc:postgresql://ep-shiny-water-a6xpmjed.us-west-2.retooldb.com/retool?sslmode=require";
        String user = "retool";
        String password = "Ox3yXF2lsZdg";
        String sql = "select * from horarios_indisponiveis";

        try (Connection conn = getConnection(url, user, password)) {

            System.out.println("Conectado com sucesso!");
            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery(sql);
            ArrayList<estruturaDaLista> lista = new ArrayList<>();

            while (res.next()) {
                String dia = res.getString(1);
                String horario = res.getString(2);
                lista.add(new estruturaDaLista(dia, horario));
                System.out.println("Dia:" + dia + "Horário" + horario);
            }
            for(estruturaDaLista d : lista) {
                System.out.println(d);
            }
        } catch (SQLException e) {
            System.out.println("Erro de conexão: " + e.getMessage());
        }
    }

    static class estruturaDaLista{
         @Getter
         String dia;
         String hora;

        public estruturaDaLista(String dia, String hora) {
            this.dia = dia;
            this.hora = hora;
        }
    }
}
