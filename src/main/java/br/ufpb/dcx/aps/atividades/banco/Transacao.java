package br.ufpb.dcx.aps.atividades.banco;

import java.util.Calendar;

public class Transacao {

    private int id;
    private Calendar data;
    private double valor;


    public Transacao(double valor) {
        this.id = id++;
        this.data = Calendar.getInstance();
        this.valor = valor;
    }


    public int getId() {
        return id;
    }


    public double getValor() {
        return valor;
    }


    @Override
    public String toString() {
        return "Transacao [id=" + id + ", data=" + data + ", valor=" + valor + "]";
    }

}