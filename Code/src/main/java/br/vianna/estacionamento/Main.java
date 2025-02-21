package br.vianna.estacionamento;


import br.vianna.estacionamento.model.Cliente;

import br.vianna.estacionamento.model.Estacionamento;
import br.vianna.estacionamento.model.Estacionar;
import br.vianna.estacionamento.model.Veiculo;
import br.vianna.estacionamento.model.dao.ClienteDAO;
import br.vianna.estacionamento.model.dao.EstacionamentoDAO;
import br.vianna.estacionamento.model.dao.EstacionarDAO;
import br.vianna.estacionamento.model.dao.VeiculoDAO;
import br.vianna.estacionamento.model.e.ETipoPlano;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

//        LocalDateTime ld = LocalDateTime.now();
//        Estacionar est = new Estacionar(ld, new VeiculoDAO().buscarPelaPlaca("jhn1234"), 4);
//        EstacionarDAO estDAO = new EstacionarDAO();
//        estDAO.inserir(est);
//        est.registrarSaida();
//        estDAO.alterar(est);

        Veiculo vei = new VeiculoDAO().buscarPelaPlaca("abc1234");
        System.out.println(vei.getPlaca());
        vei.setEstacionar(new EstacionarDAO().buscarPlaca("abc1234"));
        System.out.println(vei.getPlano());
        System.out.println(vei.getEstacionar().size());
        Estacionamento est = new EstacionamentoDAO().buscar();
        double preco = est.preco(vei);
        System.out.println(preco);


    }
}
