package br.vianna.estacionamento.model.dao;


import br.vianna.estacionamento.model.Funcionario;
import br.vianna.estacionamento.model.faces.IGenericsDao;
import br.vianna.estacionamento.util.conenction.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FuncionarioDAO implements IGenericsDao<Funcionario> {
    @Override
    public void inserir(Funcionario func) throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnection();
        String sql ="INSERT INTO estacionamento.funcionario" +
                "(login, senha)" +
                "VALUES( ?, ?)";
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setString(1, func.getLogin());
        ps.setString(2, func.getSenha());

        ps.execute();
    }

    @Override
    public void alterar(Funcionario func) throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnection();
        String sql = "UPDATE estacionamento.funcionario " +
                "SET  login = ? , senha= ? " +
                "WHERE id=?;";

        PreparedStatement ps = c.prepareStatement(sql);
        ps.setString(1, func.getLogin());
        ps.setString(2, func.getSenha());
        ps.setInt(3, func.getId());

        ps.execute();
    }

    public void apagar(int key) throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnection();
        String sql =" DELETE FROM estacionamento.funcionario WHERE id=?;";

        PreparedStatement ps = c.prepareStatement(sql);
        ps.setInt(1, key);

        ps.execute();
    }


    public Funcionario buscarPelaChave(int key) throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnection();
        String sql ="SELECT login, senha" +
                " FROM estacionamento.funcionario" +
                "WHERE ID = ?;";
        PreparedStatement ps = c.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        Funcionario func = null;
        if (rs.next()){
            func = new Funcionario(
                    rs.getString("login"),
                    rs.getString("senha"),
                    rs.getInt("id"));
        }else{
            func = null;
        }
        return func;
    }


    public ArrayList<Funcionario> buscarTodos() throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnection();
        String sql ="SELECT login, senha, id" +
                " FROM estacionamento.funcionario";

        PreparedStatement ps = c.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        ArrayList<Funcionario> funcionarios = new ArrayList<>();

        while (rs.next()){
            Funcionario func = new Funcionario(
                    rs.getString("login"),
                    rs.getString("senha"),
                    rs.getInt("id"));

            funcionarios.add(func);
        }
        return funcionarios;
    }

    public Funcionario buscarPorLoginESenha(String login, String senha) throws SQLException,ClassNotFoundException{
        Connection c = ConnectionFactory.getConnection();
        String sql = "SELECT  login, senha, id FROM estacionamento.funcionario WHERE login = ? AND senha = ?;";
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setString(1,login);
        ps.setString(2,senha);
        ResultSet rs = ps.executeQuery();

        Funcionario func = null;
        if (rs.next()){
            func = new Funcionario(
                    rs.getString("login"),
                    rs.getString("senha"));

        }else{
            func = null;
        }
        return func;
    }
}
