package br.vianna.estacionamento.model;

import br.vianna.estacionamento.model.e.ETipoUsuario;

import java.util.ArrayList;

public class Cliente extends Usuario{
    private ArrayList<Veiculo> listaVeiculo;


    public Cliente(String string, String login, String senha, int id) {
        listaVeiculo = new ArrayList<Veiculo>();
    }

    public Cliente(int id, String nome, String cpf, String login, String senha) {
        super(id, nome, cpf, login, senha , ETipoUsuario.CLIENTE);
        listaVeiculo = new ArrayList<Veiculo>();
    }

    public Cliente(String nome, String cpf, String login, String senha, int id) {
        super(nome, cpf, login, senha, id);
    }
    public ArrayList<Veiculo> getListaVeiculo() {
        return listaVeiculo;
    }
    public void adicionarVeiculo(Veiculo v) {
        listaVeiculo.add(v);
    }

}
