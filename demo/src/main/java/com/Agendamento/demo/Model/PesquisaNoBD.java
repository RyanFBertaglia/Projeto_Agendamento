package com.Agendamento.demo.Model;

import java.sql.*;
import java.util.ArrayList;

import com.Agendamento.demo.Entities.EstruturaDaLista;


public class PesquisaNoBD {
    public static  ArrayList<EstruturaDaLista> BuscaHorario(String diaDeBusca){
        String sql = "SELECT * FROM horarios_indisponiveis WHERE dia = ?::date";
        ArrayList<EstruturaDaLista> lista = new ArrayList<>();

        try (Connection conn = ConectaAoBancoDeDados.Conexao();
             PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, diaDeBusca);
            ResultSet res = stmt.executeQuery();


            while (res.next()) {
                String dia = res.getString(1);
                String horario = res.getString(2);
                lista.add(new EstruturaDaLista(dia, horario));
            }

            System.out.println(lista);
            return lista;
        }catch (SQLException e) {
             System.out.println("Erro de conexão: " + e.getMessage());
        }
        return new ArrayList<>();
    }
}