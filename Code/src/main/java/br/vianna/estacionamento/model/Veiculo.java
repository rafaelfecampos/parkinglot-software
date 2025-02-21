package br.vianna.estacionamento.model;

import br.vianna.estacionamento.model.e.ETipoPlano;

import java.util.ArrayList;


public class Veiculo {
    private Cliente dono;
    private ETipoPlano plano;
    private String placa;
    private String modelo;
    private ArrayList<Estacionar> estacionar;
    private int id_estacionarSemSaida;

    public Veiculo(Cliente dono,String modelo, ETipoPlano tipoPlano, String placa) {
        this.dono=dono;
        this.modelo = modelo;
        this.plano = tipoPlano;
        this.placa = placa;
        estacionar = new ArrayList<>();
    }

    public Cliente getDono() {
        return dono;
    }

    public void setDono(Cliente dono) {
        this.dono = dono;
    }

    public ETipoPlano getPlano() {
        return plano;
    }

    public void setPlano(ETipoPlano plano) {
        this.plano = plano;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public ArrayList<Estacionar> getEstacionar() {
        return estacionar;
    }
    public void estacionar(Estacionar e) {
        estacionar.add(e);
    }

    public void setEstacionar(ArrayList<Estacionar> estacionar) {
        this.estacionar = estacionar;
    }

    public int getId_estacionar() {
        return id_estacionarSemSaida;
    }

    public void setId_estacionar(int id_estacionar) {
        this.id_estacionarSemSaida = id_estacionar;
    }
}
