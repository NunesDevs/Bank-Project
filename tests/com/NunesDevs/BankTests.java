package com.NunesDevs;

import java.math.BigDecimal;
import java.util.Arrays;

public final class BankTests {
    private static int checks;
    public static void main(String[] args) {
        Cliente cliente = new Cliente(); cliente.setNome("Pessoa de Teste");
        cliente.setCpf("529.982.247-25"); cliente.setData("01/01/2000");
        rejects(() -> cliente.setCpf("11111111111"));
        rejects(() -> cliente.setCpf("52998224724"));
        rejects(() -> cliente.setData("31/02/2000"));
        rejects(() -> cliente.setNome(" "));
        Conta a = new ContaC(cliente); Conta b = new ContaP(cliente);
        check(!a.getNumero().equals(b.getNumero()), "Números únicos");
        a.depositar(new BigDecimal("0.10")); a.depositar(new BigDecimal("0.20")); equal(a, "0.30");
        for (double bad : new double[]{-1, 0, Double.NaN, Double.POSITIVE_INFINITY, 0.001}) rejects(() -> a.depositar(bad));
        rejects(() -> a.sacar(1)); rejects(() -> a.transferir(1, b));
        rejects(() -> a.transferir(0.1, null)); rejects(() -> a.transferir(0.1, a));
        equal(a, "0.30"); equal(b, "0.00");
        check(a.getHistorico().size() == 2, "Operações inválidas não alteram histórico");
        a.transferir(new BigDecimal("0.10"), b); equal(a, "0.20"); equal(b, "0.10");
        a.sacar(new BigDecimal("0.20")); equal(a, "0.00");
        try { a.getHistorico().clear(); throw new AssertionError("Histórico mutável"); }
        catch (UnsupportedOperationException expected) { checks++; }
        Banco banco = new Banco(); check(banco.getContas().isEmpty(), "Lista inicializada");
        banco.setContas(Arrays.asList(a,b)); rejects(() -> banco.setContas(Arrays.asList(a,a)));
        check(banco.getContas().size() == 2, "Lista preservada em erro");
        System.out.println(checks + " verificações bancárias aprovadas.");
    }
    private static void equal(Conta conta, String esperado) { check(conta.getSaldoDecimal().equals(new BigDecimal(esperado)), "Saldo " + esperado); }
    private static void check(boolean ok, String message) { if (!ok) throw new AssertionError(message); checks++; }
    private static void rejects(Runnable action) {
        try { action.run(); throw new AssertionError("Valor inválido aceito"); }
        catch (IllegalArgumentException expected) { checks++; }
    }
}
