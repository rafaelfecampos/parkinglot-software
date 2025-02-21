package br.vianna.estacionamento.model;

import br.vianna.estacionamento.model.e.ETipoPlano;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Estacionar {
    private LocalDateTime entrada;
    private LocalDateTime saida;
    private Veiculo veiculo;
    private int id;


    public Estacionar(LocalDateTime e, LocalDateTime s, Veiculo v, int id) {
        this.entrada = e;
        this.saida =s;
        this.veiculo = v;
        this.id = id;
    }

    public Estacionar(LocalDateTime e, Veiculo v, int id) {
        this.entrada = e;
        this.veiculo = v;
        this.id = id;
    }


    public LocalDateTime getEntrada() {

        return entrada;
    }

    public void registrarSaida() {
        if (entrada != null) {
            saida = LocalDateTime.now();
        }
    }

    public LocalDateTime getSaida() {
        if(saida == null) {
            return LocalDateTime.parse("0001-01-01T00:00:00");
        } else {
            return saida;
        }
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
