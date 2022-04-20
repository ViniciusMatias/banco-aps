package br.ufpb.dcx.aps.atividades.banco;

import java.util.Objects;
import java.util.regex.Pattern;

public class Correntista {
    private String cpf;
    private String nome;
    private Conta conta;

    public Correntista(String cpf, String nome) {
        this.cpf = cpf;
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public String getCpf() {
        return cpf;
    }

    public static boolean cpfValido(String cpf){
        if(cpf.length() < 11) {
            return false;
        }
        if(cpf == ""){
            return false;
        }
        String patternNumerosRepetidos = "([0]{11}|[1]{11}|[2]{11}|[3]{11}|[4]{11}|[5]{11}|[6]{11}|[7]{11}|[8]{11}|[9]{11})";
        boolean hasValidate = Pattern.matches(patternNumerosRepetidos, cpf);
        return !hasValidate;
    }

    public static String removeSeparadores(String comSeparadores) {
        String semSeparadores = "";

        String [] array =  comSeparadores.split("\\.|-" );
        for (String numeros: array) {
            semSeparadores += numeros;
        }
        return semSeparadores;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Correntista that = (Correntista) o;
        return Objects.equals(cpf, that.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }
}
