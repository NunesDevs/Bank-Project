package com.NunesDevs;

public class ContaP extends Conta{

    public ContaP(Cliente cliente) {
        super(cliente);
    }

    @Override
    public void imprimirExtrato() {
        System.out.println("Conta poupança");

        super.imprimirInfo();

    }
    public void imprimirSaldo() {
        System.out.println("Conta poupança");

        super.imprimirSaldo();
    }
    public void imprimirConta() {
        System.out.println("Conta poupança");

        super.imprimirConta();
    }
}
