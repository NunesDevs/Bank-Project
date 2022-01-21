package com.NunesDevs;

import java.util.Random;

public abstract class Conta implements IConta{


    private static final int AGENCIA = 0001;

    protected int agencia;
    protected String numero;
    protected double saldo;
    protected Cliente cliente;

    Random gerador = new Random();

    int n1 = 1 * gerador.nextInt(9);
    int n2 = 1 * gerador.nextInt(9);
    int n3 = 1 * gerador.nextInt(9);
    int n4 = 1 * gerador.nextInt(9);
    int n5 = 1 * gerador.nextInt(9);
    int d1 = 1 * gerador.nextInt(9);
    int d2 = 1 * gerador.nextInt(9);



    int num = n1 + n2 + n3 + n4 + n5;

    String numeroConta = "" + n1 + n2 + n3 + n4 + n5 + "-" + d1 + d2;

    private static int SEQUENCIA = 111111;

    public Conta(Cliente cliente){
        this.agencia = Conta.AGENCIA;
        this.numero = numeroConta;
        this.cliente = cliente;
    }
    public void sacar(){}

    public void depositar(){}

    public void transferir(){}

    @Override
    public void sacar(double valor) {
        this.saldo = saldo - valor;
    }

    @Override
    public void depositar(double valor) {
        this.saldo = saldo + valor;
    }

    @Override
    public void transferir(double valor, Conta cDestino) {
        this.sacar(valor);
        cDestino.depositar(valor);
    }

    public int getAgencia() {
        return agencia;
    }

    public String getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    protected void imprimirInfo() {
        System.out.println(String.format("Titular: %s", this.cliente.getNome()));

        System.out.println(String.format("Agencia: %d", this.agencia));

        System.out.println(String.format("Conta: %s", this.numero));

        System.out.println(String.format("Saldo: %.2f", this.saldo));
    }

    protected void imprimirSaldo() {
        System.out.println(String.format("Titular: %s", this.cliente.getNome()));

        System.out.println(String.format("Saldo: %.2f", this.saldo));
    }

    protected void imprimirConta() {
        System.out.println(String.format("Titular: %s", this.cliente.getNome()));

        System.out.println(String.format("Documento: %s", this.cliente.getCpf()));

        System.out.println(String.format("Nascimento: %s", this.cliente.getData()));

        System.out.println(String.format("Agencia: %d", this.agencia));

        System.out.println(String.format("Conta: %s", this.numero));

    }




}
