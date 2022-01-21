package com.NunesDevs;

public interface IConta {
    void sacar(double valor);

    void depositar(double valor);

    void transferir(double valor, Conta cDestino);

    void imprimirExtrato();
}
