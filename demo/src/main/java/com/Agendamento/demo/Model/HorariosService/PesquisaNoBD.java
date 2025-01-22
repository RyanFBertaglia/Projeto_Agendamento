package com.Agendamento.demo.Model.HorariosService;

import java.sql.*;
import java.util.ArrayList;

import com.Agendamento.demo.Entities.EstruturaDaLista;
import com.Agendamento.demo.exceptions.DataEnviadaErrada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PesquisaNoBD {

    private final AcessoDB acessoDB;

    @Autowired
    public PesquisaNoBD(AcessoDB acessoDB) {
        this.acessoDB = acessoDB;
    }


    public ArrayList<EstruturaDaLista> BuscaHorario(String diaDeBusca) {
        String sql = "SELECT * FROM horarios_indisponiveis WHERE dia = ?::date";
        ArrayList<EstruturaDaLista> lista = new ArrayList<>();


        try (Connection conn = acessoDB.Conexao();
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
            if("22007".equals(e.getSQLState()) || "22018".equals(e.getSQLState())){
                throw new DataEnviadaErrada();
            } else{
                return new ArrayList<>();
            }
        }
    }
}