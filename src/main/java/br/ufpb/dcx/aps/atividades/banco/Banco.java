package br.ufpb.dcx.aps.atividades.banco;

import br.ufpb.dcx.aps.atividades.banco.exception.BancoException;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Banco {

    private  Map<Correntista,Conta> contasCorrentista;
    private String nome;
    private Map<Integer, Conta> contas;
    private int numeroContaLivre = 0;
    private Map<String, Correntista> correntistas;

    public Banco(String nome) {
        this.nome = nome;
        this.contas = new HashMap<Integer, Conta>();
        this.correntistas = new HashMap<String, Correntista>();
        this.contasCorrentista = new HashMap<Correntista,Conta>();
    }

    public Conta criarConta(Correntista correntista) throws BancoException{
        if(!this.correntistas.containsValue(correntista)) throw new BancoException(String.format("Correntista não cadastrado no banco:Correntista{cpf='%s', nome='%s'}", correntista.getCpf(), correntista.getNome()));
        Conta conta = new Conta(correntista, numeroContaLivre, this);
        this.contas.put(this.gerarNumeroConta(), conta);
        this.contasCorrentista.put(correntista,conta);
        numeroContaLivre++;
        return conta;
    }

    public Conta getConta(int numero) {
        return this.contas.get(numero);
    }

    public Conta getConta(Correntista titular) {
        return this.contasCorrentista.get(titular);
    }

    public Map<Integer, Conta> getAllContas(){
        return this.contas;
    }

    public void addCorrentista(Correntista correntista) {
      this.correntistas.put(correntista.getCpf(), correntista);
    }

    public Correntista getCorrentista(String cpf) {
        return this.correntistas.get(cpf);
    }

    public Integer gerarNumeroConta() {
        return numeroContaLivre;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


}
