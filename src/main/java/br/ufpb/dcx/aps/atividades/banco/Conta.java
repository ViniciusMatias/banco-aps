package br.ufpb.dcx.aps.atividades.banco;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Conta {

    private Correntista correntista;
    private double saldo;
    private Integer numero;
    private Banco banco;
    private List<Transacao> transacoes = new ArrayList<>();

    public Conta(Correntista correntista, Integer numero, Banco banco) {
        this.correntista = correntista;
        this.correntista.setConta(this);
        this.banco = banco;
        this.saldo = saldo;
        this.numero = numero;
    }

    public Transacao depositar(double valor) {
        saldo += valor;
        Transacao transacao = new Transacao(valor);
        this.transacoes.add(transacao);
        return transacao;
    }

    public Transacao sacar(double valor) {
        saldo -= valor;
        Transacao transacao = new Transacao(-valor);
        this.transacoes.add(transacao);
        return transacao;
    }

    public double saldo() {
        return this.saldo;
    }

    public String extrato() {
        String transacoesFeitas = "";
        String saldo = String.format("SALDO:\tR$ %2.2f", this.saldo);


        for(Transacao transacao: this.transacoes) {
            if(transacao.getValor() > 0)  transacoesFeitas += String.format("CRÉDITO\tR$ %2.2f\n", transacao.getValor());
            if(transacao.getValor() < 0)  {
                double valor = -1 * transacao.getValor();
                transacoesFeitas += String.format("DÉBITO\t-R$ %2.2f\n", valor);
            }
        }
        String extrato = ">> "+this.banco.getNome()+"\n" +
                ">> Correntista: \n" +
                " CPF: "+this.correntista.getCpf()+"\n" +
                " "+this.correntista.getNome()+"\n" +
                "> EXTRATO\n" +
                "------------------------------------\n" +
                transacoesFeitas
                +
                "------------------------------------\n" +
                saldo;
        return extrato;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public Correntista getCorrentista() {
        return correntista;
    }

    public void setCorrentista(Correntista correntista) {
        this.correntista = correntista;
    }
}
