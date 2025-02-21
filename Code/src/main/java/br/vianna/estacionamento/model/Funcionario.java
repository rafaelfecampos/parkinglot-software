package br.vianna.estacionamento.model;

import br.vianna.estacionamento.model.e.ETipoUsuario;

public class Funcionario extends Usuario {
    public Funcionario(int id, String nome, String cpf, String login, String senha) {
        super(id, nome, cpf, login, senha, ETipoUsuario.FUNCIONARIO);
    }

    public Funcionario() {
    }

    public Funcionario(String login, String senha, int id) {
        super(login,senha,id);
    }



    public Funcionario(String login, String senha) {
        super(login,senha);
    }

}
