package br.vianna.estacionamento.model;

import br.vianna.estacionamento.model.e.ETipoPlano;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;


public class Estacionamento {
    private ArrayList<Veiculo> listaVeiculos;
    private double valorHora, valorDiaria, valorSemanal, valorMensal;
    private int id;

    public Estacionamento() {
        listaVeiculos = new ArrayList<>();
    }

    public Estacionamento(double valorHora, double valorDiaria, double valorSemanal, double valorMensal) {
        this.valorHora = valorHora;
        this.valorDiaria = valorDiaria;
        this.valorSemanal = valorSemanal;
        this.valorMensal = valorMensal;
        listaVeiculos = new ArrayList<>();
    }
    public Estacionamento(int id,double valorMensal, double valorSemanal, double valorDiaria, double valorHora) {
        this.id=id;
        this.valorHora = valorHora;
        this.valorDiaria = valorDiaria;
        this.valorSemanal = valorSemanal;
        this.valorMensal = valorMensal;
        listaVeiculos = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Veiculo> getListaVeiculos() {
        return listaVeiculos;
    }

    public void adicionarVeiculo(Veiculo v) {
        listaVeiculos.add(v);
    }

    public double getValorHora() {
        return valorHora;
    }

    public void setValorHora(double valorHora) {
        this.valorHora = valorHora;
    }

    public double getValorDiaria() {
        return valorDiaria;
    }

    public void setValorDiaria(double valorDiaria) {
        this.valorDiaria = valorDiaria;
    }

    public double getValorSemanal() {
        return valorSemanal;
    }

    public void setValorSemanal(double valorSemanal) {
        this.valorSemanal = valorSemanal;
    }

    public double getValorMensal() {
        return valorMensal;
    }

    public void setValorMensal(double valorMensal) {
        this.valorMensal = valorMensal;
    }

    public void setListaVeiculos(ArrayList<Veiculo> listaVeiculos) {
        this.listaVeiculos = listaVeiculos;
    }

    public void attVeiculo(Veiculo vei) {
        for (Veiculo v :
                listaVeiculos) {
            if (vei.getPlaca().equals(v.getPlaca())) {
                v.setModelo(vei.getModelo());
                v.setPlano(v.getPlano());
                v.setDono(v.getDono());
            }
            }
    }

    public int tempo(Veiculo v) {
        double tempo = 0;
        ArrayList<Estacionar> est = v.getEstacionar();
        if (!est.isEmpty()) {
            LocalDateTime entrada = est.get(0).getEntrada();
            LocalDateTime saida = est.get(est.size() - 1).getSaida();

            switch (v.getPlano()) {
                case MENSAL:
                    tempo = (double) ChronoUnit.MONTHS.between(entrada, saida);
                    System.out.println("mes");
                    break;
                case SEMANAL:
                    tempo = (double) ChronoUnit.WEEKS.between(entrada, saida);
                    System.out.println("seaman");

                    break;
                case DIARIA:
                    System.out.println("dia");

                    tempo = (double) ChronoUnit.DAYS.between(entrada, saida);
                    break;
                case HORA:
                    System.out.println("hora");

                    tempo = (double) ChronoUnit.HOURS.between(entrada, saida);
                    break;
                default:
                    break;
            }
        }
        return (int) Math.ceil(tempo);
    }


    public double preco(Veiculo v) {
        if (v != null && v.getPlano() != null) {
            double tempo = tempo(v);
            switch (v.getPlano()) {
                case MENSAL:
                    return getValorMensal() * tempo;
                case SEMANAL:
                    return getValorSemanal() * tempo;
                case DIARIA:
                    return getValorDiaria() * tempo;
                case HORA:
                    return getValorHora() * tempo;
                default:
                    return 0;
            }
        }
        return 0;
    }


}
