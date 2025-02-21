package br.vianna.estacionamento.model.dao;

import br.vianna.estacionamento.model.Cliente;
import br.vianna.estacionamento.model.faces.IGenericsDao;
import br.vianna.estacionamento.util.conenction.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClienteDAO implements IGenericsDao<Cliente> {


    @Override
    public void inserir(Cliente cli) throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnection();
        String sql = "INSERT INTO estacionamento.cliente" +
                "(nome, cpf, login, senha)" +
                "VALUES(  ?, ?, ?, ?)";
        VeiculoDAO v = new VeiculoDAO();
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setString(1, cli.getNome());
        ps.setString(2, cli.getCpf());
        ps.setString(3, cli.getLogin());
        ps.setString(4, cli.getSenha());

        ps.execute();
    }

    @Override
    public void alterar(Cliente cli) throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnection();
        String sql = "UPDATE estacionamento.cliente " +
                "SET nome=? , cpf=? , login = ? , senha= ? " +
                "WHERE id=?;";

        PreparedStatement ps = c.prepareStatement(sql);
        ps.setString(1, cli.getNome());
        ps.setString(2, cli.getCpf());
        ps.setString(3, cli.getLogin());
        ps.setString(4, cli.getSenha());
        ps.setInt(5, cli.getId());

        ps.execute();
    }


    public void apagar(int key) throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnection();
        String sql = " DELETE FROM estacionamento.cliente WHERE id=?;";

        PreparedStatement ps = c.prepareStatement(sql);
        ps.setInt(1, key);

        ps.execute();
    }


    public Cliente buscarPelaChave(int key) throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnection();
        String sql = "SELECT nome, cpf, login, senha, id FROM estacionamento.cliente WHERE id = ?;";
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setInt(1, key);
        ResultSet rs = ps.executeQuery();

        Cliente cli = null;
        if (rs.next()) {
            cli = new Cliente(rs.getString("nome"),
                    rs.getString("cpf"),
                    rs.getString("login"),
                    rs.getString("senha"),
                    rs.getInt("id"));
        } else {
            cli = null;
        }
        return cli;


    }

    public Cliente buscarPorLoginESenha(String login, String senha) throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnection();
        String sql = "SELECT nome, cpf, login, senha, id FROM estacionamento.cliente WHERE login = ? AND senha = ?;";
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setString(1, login);
        ps.setString(2, senha);
        ResultSet rs = ps.executeQuery();

        Cliente cli = null;
        if (rs.next()) {
            cli = new Cliente(rs.getString("nome"),
                    rs.getString("cpf"),
                    rs.getString("login"),
                    rs.getString("senha"),
                    rs.getInt("id"));
        } else {
            cli = null;
        }
        return cli;
    }


    public ArrayList<Cliente> buscarTodos() throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnection();
        String sql = "SELECT nome, cpf, login, senha, id" +
                " FROM estacionamento.cliente";

        PreparedStatement ps = c.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        ArrayList<Cliente> clientes = new ArrayList<>();

        while (rs.next()) {
            Cliente cli = new Cliente(rs.getString("nome"),
                    rs.getString("cpf"),
                    rs.getString("login"),
                    rs.getString("senha"),
                    rs.getInt("id"));

            clientes.add(cli);
        }

        return clientes;
    }

    public ArrayList<Cliente> buscarNome(String nome) throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnection();
        String sql = "SELECT nome, cpf, login, senha, id FROM estacionamento.cliente WHERE nome like ?;";
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setString(1, "%" + nome + "%");

        ResultSet rs = ps.executeQuery();

        ArrayList<Cliente> clientes = new ArrayList<>();
        while (rs.next()) {
            Cliente cli = new Cliente(rs.getString("nome"),
                    rs.getString("cpf"),
                    rs.getString("login"),
                    rs.getString("senha"),
                    rs.getInt("id"));
            clientes.add(cli);
        }
        return clientes;
    }

    public Cliente buscarPorCpf(String key) throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnection();
        String sql = "SELECT nome, cpf, login, senha, id FROM estacionamento.cliente WHERE cpf = ?;";
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setString(1, key);
        ResultSet rs = ps.executeQuery();

        Cliente cli = null;
        if (rs.next()) {
            cli = new Cliente(rs.getString("nome"),
                    rs.getString("cpf"),
                    rs.getString("login"),
                    rs.getString("senha"),
                    rs.getInt("id"));
        } else {
            cli = null;
        }
        return cli;
    }


}
