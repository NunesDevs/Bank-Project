package com.NunesDevs;

public class ContaC extends Conta{
    public ContaC(Cliente cliente) {
        super(cliente);
    }

    @Override
    public void imprimirExtrato() {
        System.out.println("Conta corrente");

        super.imprimirInfo();
    }
    public void imprimirSaldo() {
        System.out.println("Conta corrente");

        super.imprimirSaldo();
    }
    public void imprimirConta() {
        System.out.println("Conta corrente");

        super.imprimirConta();
    }


}
