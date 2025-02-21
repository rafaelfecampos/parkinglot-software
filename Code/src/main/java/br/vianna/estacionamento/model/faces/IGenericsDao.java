package br.vianna.estacionamento.model.faces;

import java.sql.SQLException;

public interface IGenericsDao<C> {
    public void inserir(C x)
            throws SQLException, ClassNotFoundException;
    public void alterar(C x)
            throws SQLException, ClassNotFoundException;
}
