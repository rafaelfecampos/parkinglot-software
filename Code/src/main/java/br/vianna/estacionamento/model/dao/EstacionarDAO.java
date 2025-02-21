package br.vianna.estacionamento.model.dao;


import br.vianna.estacionamento.model.Estacionar;
import br.vianna.estacionamento.model.faces.IGenericsDao;
import br.vianna.estacionamento.util.conenction.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class EstacionarDAO implements IGenericsDao<Estacionar> {
    @Override
    public void inserir(Estacionar est) throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnection();
        String sql ="INSERT INTO estacionamento.estacionar " +
                "(id_estacionamento, entrada, saida, placa_carro)" +
                " VALUES(0 , ?,?, ?);";
        PreparedStatement ps = c.prepareStatement(sql);
        EstacionamentoDAO estacionamentoDAO = new EstacionamentoDAO();
//        ps.setInt(1,1);
        ps.setString(1,est.getEntrada().toString());
        ps.setString(2,est.getSaida().toString());
        ps.setString(3,est.getVeiculo().getPlaca());
        ps. execute();


    }

    @Override
    public void alterar(Estacionar est) throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnection();
        String sql = "UPDATE estacionamento.estacionar SET  saida=? " +
                "WHERE id = ?;";

        PreparedStatement ps = c.prepareStatement(sql);
        ps.setString(1,est.getSaida().toString());;
        ps.setInt(2,est.getId());

        ps.execute();
    }

    public Estacionar buscarPelaChaveSemSaida(int key) throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnection();
        String sql ="SELECT id,entrada, saida, placa_carro FROM estacionamento.estacionar WHERE id = ?;";
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setInt(1,key);
        ResultSet rs = ps.executeQuery();
        VeiculoDAO v = new VeiculoDAO();
        Estacionar est = null;
        if (rs.next()){
            est = new Estacionar(
                    LocalDateTime.parse(rs.getString("entrada")),
//                    LocalDateTime.parse(rs.getString("saida")),
                    v.buscarPelaPlaca(rs.getString("placa_carro")),
                    rs.getInt("id")
            );
        }else{
            est = null;
        }
        return est;
    }
    public int buscarIdSemSaida(String placa)throws SQLException, ClassNotFoundException{
        Connection c = ConnectionFactory.getConnection();
        String sql= "SELECT id,entrada, saida, placa_carro FROM estacionamento.estacionar " +
                "WHERE placa_carro = ? and saida=\"0001-01-01T00:00\"";
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setString(1,placa);
        ResultSet rs = ps.executeQuery();
        int id = -1;
        if(rs.next()){
            id = rs.getInt("id");
        }
        return id;
    }

    public ArrayList<Estacionar> buscarPlaca(String placa)throws SQLException, ClassNotFoundException{
        Connection c = ConnectionFactory.getConnection();
        String sql = "SELECT id, entrada, saida, placa_carro FROM estacionamento.estacionar WHERE placa_carro = ?;";
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setString(1,placa);
        ResultSet rs = ps.executeQuery();
        ArrayList<Estacionar> estacionar = new ArrayList<>();
        VeiculoDAO v = new VeiculoDAO();
        while (rs.next()){
            Estacionar est = new Estacionar(
                    LocalDateTime.parse(rs.getString("entrada")),
                    LocalDateTime.parse(rs.getString("saida")),
                    v.buscarPelaPlaca(rs.getString("placa_carro")),
                    rs.getInt("id"));

            estacionar.add(est);
        }
        return estacionar;
    }
}
