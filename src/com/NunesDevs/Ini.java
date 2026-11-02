package com.NunesDevs;

import java.math.BigDecimal;
import java.util.Scanner;
import java.util.function.Consumer;

public final class Ini {
    private Ini() { }
    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in, "UTF-8")) {
            Cliente cliente = new Cliente();
            if (!lerCadastro(input, "Digite seu nome: ", cliente::setNome)
                || !lerCadastro(input, "Digite seu CPF (apenas para simulação): ", cliente::setCpf)
                || !lerCadastro(input, "Nascimento (dd/MM/aaaa): ", cliente::setData)) return;
            Conta corrente = new ContaC(cliente);
            Conta poupanca = new ContaP(cliente);
            while (true) {
                String option = ler(input, "\n1 - Depositar\n2 - Sacar\n3 - Transferir\n4 - Extrato\n5 - Dados da conta\n6 - Encerrar");
                if (option == null || option.equals("6")) break;
                try {
                    if (option.equals("4")) { corrente.imprimirExtrato(); poupanca.imprimirExtrato(); continue; }
                    if (option.equals("5")) { corrente.imprimirConta(); poupanca.imprimirConta(); continue; }
                    if (!option.matches("[123]")) { System.out.println("Opção inválida."); continue; }
                    String account = ler(input, "Conta de origem/operação: 1 - Corrente | 2 - Poupança");
                    if (account == null) break;
                    if (!account.matches("[12]")) { System.out.println("Conta inválida."); continue; }
                    Conta origem = account.equals("1") ? corrente : poupanca;
                    String amount = ler(input, "Valor (ex.: 10,50, sem separador de milhar): ");
                    if (amount == null) break;
                    if (!amount.matches("[0-9]+([.,][0-9]{1,2})?")) throw new IllegalArgumentException("Valor inválido; use no máximo duas casas decimais.");
                    BigDecimal valor = new BigDecimal(amount.replace(',', '.'));
                    if (option.equals("1")) origem.depositar(valor);
                    else if (option.equals("2")) origem.sacar(valor);
                    else origem.transferir(valor, origem == corrente ? poupanca : corrente);
                    System.out.println("Operação concluída.");
                } catch (IllegalArgumentException error) { System.out.println(error.getMessage()); }
            }
            System.out.println("Sessão encerrada. Dados da simulação descartados.");
        }
    }
    private static String ler(Scanner input, String prompt) {
        System.out.println(prompt);
        return input.hasNextLine() ? input.nextLine().trim() : null;
    }
    private static boolean lerCadastro(Scanner input, String prompt, Consumer<String> setter) {
        while (true) {
            String value = ler(input, prompt);
            if (value == null) return false;
            try { setter.accept(value); return true; }
            catch (IllegalArgumentException error) { System.out.println(error.getMessage()); }
        }
    }
}
