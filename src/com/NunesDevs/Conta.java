package com.NunesDevs;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

/** Conta de um simulador em memória. Operações monetárias usam centavos exatos. */
public abstract class Conta implements IConta {
    private static final AtomicLong SEQUENCIA = new AtomicLong(100000);
    private static final Object OPERACOES = new Object();
    private final int agencia = 1;
    private final String numero;
    private final Cliente cliente;
    private BigDecimal saldo = new BigDecimal("0.00");
    private final List<String> historico = new ArrayList<>();

    protected Conta(Cliente cliente) {
        this.cliente = Objects.requireNonNull(cliente, "Titular obrigatório.");
        if (cliente.getNome() == null) throw new IllegalArgumentException("Nome do titular obrigatório.");
        numero = Long.toString(SEQUENCIA.incrementAndGet());
    }
    private static BigDecimal validarValor(BigDecimal valor) {
        if (valor == null || valor.signum() <= 0) throw new IllegalArgumentException("O valor deve ser maior que zero.");
        try { return valor.setScale(2, RoundingMode.UNNECESSARY); }
        catch (ArithmeticException error) { throw new IllegalArgumentException("Use no máximo duas casas decimais."); }
    }
    private static BigDecimal decimal(double valor) {
        if (!Double.isFinite(valor)) throw new IllegalArgumentException("Valor monetário inválido.");
        return BigDecimal.valueOf(valor);
    }
    @Override public void depositar(double valor) { depositar(decimal(valor)); }
    @Override public void sacar(double valor) { sacar(decimal(valor)); }
    @Override public void transferir(double valor, Conta destino) { transferir(decimal(valor), destino); }
    public void depositar(BigDecimal valor) {
        BigDecimal quantia = validarValor(valor);
        synchronized (OPERACOES) {
            saldo = saldo.add(quantia);
            historico.add("Depósito: +" + quantia.toPlainString());
        }
    }
    public void sacar(BigDecimal valor) {
        BigDecimal quantia = validarValor(valor);
        synchronized (OPERACOES) {
            validarSaldo(quantia);
            saldo = saldo.subtract(quantia);
            historico.add("Saque: -" + quantia.toPlainString());
        }
    }
    public void transferir(BigDecimal valor, Conta destino) {
        BigDecimal quantia = validarValor(valor);
        if (destino == null || destino == this) throw new IllegalArgumentException("Selecione outra conta de destino.");
        synchronized (OPERACOES) {
            validarSaldo(quantia);
            saldo = saldo.subtract(quantia);
            destino.saldo = destino.saldo.add(quantia);
            historico.add("Transferência para " + destino.numero + ": -" + quantia.toPlainString());
            destino.historico.add("Transferência de " + numero + ": +" + quantia.toPlainString());
        }
    }
    private void validarSaldo(BigDecimal valor) {
        if (saldo.compareTo(valor) < 0) throw new IllegalArgumentException("Saldo insuficiente.");
    }
    public int getAgencia() { return agencia; }
    public String getNumero() { return numero; }
    /** Compatibilidade com os exemplos originais; prefira getSaldoDecimal(). */
    public double getSaldo() { return getSaldoDecimal().doubleValue(); }
    public BigDecimal getSaldoDecimal() { synchronized (OPERACOES) { return saldo; } }
    public List<String> getHistorico() {
        synchronized (OPERACOES) { return Collections.unmodifiableList(new ArrayList<>(historico)); }
    }
    protected void imprimirInfo() {
        imprimirSaldo();
        System.out.println("Agência: " + agencia + " | Conta: " + numero);
        for (String movimento : getHistorico()) System.out.println(movimento);
    }
    protected void imprimirSaldo() {
        System.out.println("Titular: " + cliente.getNome());
        System.out.println("Saldo: R$ " + getSaldoDecimal().toPlainString());
    }
    protected void imprimirConta() {
        System.out.println("Titular: " + cliente.getNome());
        String cpf = cliente.getCpf();
        System.out.println("Documento: " + (cpf == null ? "não informado" : "***.***.***-" + cpf.substring(9)));
        System.out.println("Agência: " + agencia + " | Conta: " + numero);
    }
}
