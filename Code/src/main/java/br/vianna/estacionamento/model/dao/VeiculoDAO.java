package br.vianna.estacionamento.model.dao;


import br.vianna.estacionamento.model.Veiculo;
import br.vianna.estacionamento.model.e.ETipoPlano;
import br.vianna.estacionamento.model.faces.IGenericsDao;
import br.vianna.estacionamento.util.conenction.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VeiculoDAO implements IGenericsDao<Veiculo> {
    @Override
    public void inserir(Veiculo vei) throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnection();
        String sql = "INSERT INTO estacionamento.veiculo " + "(id_cliente, modelo, tipoPlano, placa) " +
                "VALUES(?,?,?,?);";
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setInt(1, vei.getDono().getId());
        ps.setString(2, vei.getModelo());
        ps.setString(3, vei.getPlano().toString());
        ps.setString(4, vei.getPlaca());
        ps.execute();
    }

    @Override
    public void alterar(Veiculo vei) throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnection();
        String sql = "UPDATE estacionamento.veiculo\n" +
                "SET modelo=?, tipoPlano=?, id_cliente=?\n" +
                "WHERE placa=?;";
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setString(1, vei.getModelo());
        ps.setString(2,vei.getPlano().toString());
        ps.setInt(3,vei.getDono().getId());
        ps.setString(4,vei.getPlaca());
        ps.execute();
    }


    public void apagar(String key) throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnection();
        String sql = "DELETE FROM estacionamento.veiculo " + "WHERE placa=?;";
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setString(1, key);
        ps.execute();

    }


    public Veiculo buscarPelaPlaca(String key) throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnection();
        //2º Montar Consulta SQL
        String sql = "SELECT id_cliente, modelo, tipoPlano, placa " +
                "FROM estacionamento.veiculo " +
                "WHERE placa LIKE?;";
        //3º Trocar parametos
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setString(1, "%" + key + "%");
        //4º Executar
        ResultSet rs = ps.executeQuery();
        //5º Passo, converer linhas x colunas em Objetos
        Veiculo vei = null;
        ClienteDAO cli = new ClienteDAO();
        if (rs.next()) {
            vei = new Veiculo(cli.buscarPelaChave(rs.getInt("id_cliente")),
                    rs.getString("modelo"),
                    ETipoPlano.valueOf(rs.getString("tipoPlano")),
                    rs.getString("placa"));
        } else {
            vei = null;
        }
        return vei;
    }

    public ArrayList buscarListaPelaPlaca(String key) throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnection();
        //2º Montar Consulta SQL
        String sql = "SELECT id_cliente, modelo, tipoPlano, placa " +
                "FROM estacionamento.veiculo " +
                "WHERE placa LIKE?;";
        //3º Trocar parametos
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setString(1, "%" + key + "%");
        //4º Executar
        ResultSet rs = ps.executeQuery();
        //5º Passo, converer linhas x colunas em Objetos
        Veiculo vei = null;
        ArrayList<Veiculo> veiculos = new ArrayList<>();
        ClienteDAO cli = new ClienteDAO();
        while (rs.next()) {
            vei = new Veiculo(cli.buscarPelaChave(rs.getInt("id_cliente")),
                    rs.getString("modelo"),
                    ETipoPlano.valueOf(rs.getString("tipoPlano")),
                    rs.getString("placa"));
            veiculos.add(vei);
        }
        return veiculos;
    }


    public ArrayList<Veiculo> buscarTodos() throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnection();
        String sql = "SELECT modelo, tipoPlano, placa, id_cliente " +
                "FROM estacionamento.veiculo;";

        PreparedStatement ps = c.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        ArrayList<Veiculo> veiculo = new ArrayList<>();
        ClienteDAO cli = new ClienteDAO();

        while (rs.next()) {
            Veiculo vei = new Veiculo(cli.buscarPelaChave(rs.getInt("id_cliente")),
                    rs.getString("modelo"),
                    ETipoPlano.valueOf(rs.getString("tipoPlano")),
                    rs.getString("placa"));
            veiculo.add(vei);
        }
        return veiculo;
    }
}


