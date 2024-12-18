package com.Agendamento.demo.Model.Marcar;

import java.sql.*;
import java.util.ArrayList;

import com.Agendamento.demo.Entities.EstruturaDaLista;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PesquisaNoBD {

    private final ConectaAoBancoDeDados conectaAoBancoDeDados;

    @Autowired
    public PesquisaNoBD(ConectaAoBancoDeDados conectaAoBancoDeDados) {
        this.conectaAoBancoDeDados = conectaAoBancoDeDados;
    }


    public ArrayList<EstruturaDaLista> BuscaHorario(String diaDeBusca) {
        String sql = "SELECT * FROM horarios_indisponiveis WHERE dia = ?::date";
        ArrayList<EstruturaDaLista> lista = new ArrayList<>();


        try (Connection conn = conectaAoBancoDeDados.Conexao();
             PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, diaDeBusca);
            ResultSet res = stmt.executeQuery();

            while (res.next()) {
                String dia = res.getString(1);
                String horario = res.getString(2);
                lista.add(new EstruturaDaLista(dia, horario));
            }

            return lista;
        }catch (SQLException e) {
                System.out.println("Erro: " + e.getMessage());
        }
        return new ArrayList<>();
    }
}