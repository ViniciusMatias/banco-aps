package br.ufpb.dcx.aps.atividades.banco;

import br.ufpb.dcx.aps.atividades.banco.exception.BancoException;
import br.ufpb.dcx.aps.atividades.banco.exception.ContaException;

import java.math.BigDecimal;

public class BancoFacade {

    private Banco banco;

    public BancoFacade(String nomeDoBanco) {
        this.banco = new Banco(nomeDoBanco);
    }

    public BancoFacade() {

    }
    public void cadastrarCorrentista(String cpf, String nome) throws RuntimeException {
        if(!Correntista.cpfValido(cpf)){
            throw new RuntimeException(String.format("CPF invalido:%s",cpf));
        }
        if(banco.getCorrentista(cpf) != null){
            throw new RuntimeException(String.format("Correntista ja cadastrado:Correntista{cpf='%s', nome='%s'}",cpf, nome));
        }
          banco.addCorrentista(new Correntista(cpf, nome));


    }

    public Conta criarContaPF(String cpf) throws BancoException{
        Correntista correntista = banco.getCorrentista(cpf);
        if(banco.getConta(correntista) != null) {
            throw new BancoException("Correntista já tem conta cadastrada");
        }
        if(banco.getCorrentista(cpf) == null){
            throw new RuntimeException(String.format("Não existe correntista com cpf:%s",cpf));
        }

       return banco.criarConta(banco.getCorrentista(cpf));

    }

    public Conta getConta(Integer numero) {
        return banco.getConta(numero);
    }

    public double saldo(Integer numero) {
        return banco.getConta(numero).getSaldo();
    }

    public void depositar(double valor, int numeroConta) throws ContaException {
        banco.getConta(numeroConta).depositar(valor);
    }

    public void sacar(double valor, Integer numero) throws ContaException {
        double saldoAtual = banco.getConta(numero).saldo();
        if(saldoAtual < valor ){
            throw new ContaException(String.format("Saldo insuficiente. Saldo:%2.1f - valor do saque:%2.1f", saldoAtual, valor));
        }
        banco.getConta(numero).sacar(valor);
    }

    public String extrato(Integer numero) {
        return banco.getConta(numero).extrato();
    }
}
