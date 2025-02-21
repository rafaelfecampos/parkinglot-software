package br.vianna.estacionamento.model.dao;

import br.vianna.estacionamento.model.Estacionamento;
import br.vianna.estacionamento.model.faces.IGenericsDao;
import br.vianna.estacionamento.util.conenction.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EstacionamentoDAO implements IGenericsDao<Estacionamento> {

    @Override
    public void inserir(Estacionamento est) throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnection();
        String sql = "INSERT INTO estacionamento.estacionamento " +
                "(id, valorMensal, valorSemanal, valorDiaria, ValorHora)" +
                "VALUES(0, ?, ?, ?, ?);";
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setDouble(1,est.getValorMensal());
        ps.setDouble(2,est.getValorSemanal());
        ps.setDouble(3,est.getValorDiaria());
        ps.setDouble(4,est.getValorHora());

        ps.execute();
    }

    @Override
    public void alterar(Estacionamento est) throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnection();
        String sql = "UPDATE estacionamento.estacionamento " +
                "SET valorMensal=0, valorSemanal=0, valorDiaria=0, ValorHora=0" +
                "WHERE id=?;";
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setDouble(1,est.getValorMensal());
        ps.setDouble(2,est.getValorSemanal());
        ps.setDouble(3,est.getValorDiaria());
        ps.setDouble(4,est.getValorHora());
        ps.setInt(5, est.getId());

    }



    public Estacionamento buscar() throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnection();
        String sql ="SELECT id, valorMensal, " +
                "valorSemanal, valorDiaria, ValorHora " +
                "FROM estacionamento.estacionamento;";
        PreparedStatement ps = c.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        Estacionamento est = null;

        if (rs.next()) {
            est = new Estacionamento(rs.getInt("id"),
                    rs.getDouble("valorMensal"),
                    rs.getDouble("valorSemanal"),
                    rs.getDouble("valorDiaria"),
                    rs.getDouble("valorHora"));
        }

        rs.close();
        ps.close();
        c.close();

        return est;
    }
}
