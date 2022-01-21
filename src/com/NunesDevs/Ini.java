package com.NunesDevs;

import java.util.Locale;
import java.util.Scanner;

public class Ini {


    public static void main(String[] args) {

        try (Scanner sc = new Scanner(System.in)) {
            sc.useLocale(Locale.US);

        Cliente clinovo = new Cliente();
        System.out.println("Digite seu nome: ");
        clinovo.setNome(sc.next());
        System.out.println("Digite seu cpf: ");
        clinovo.setCpf(sc.next());
        System.out.println("Digite sua data de nascimento: ");
        clinovo.setData(sc.next());

        Conta cc = new ContaC(clinovo);
        Conta cp = new ContaP(clinovo);
        cc.imprimirExtrato();
        System.out.println();
        cp.imprimirExtrato();


        do {

            System.out.println("################");
            System.out.println("# PAINEL BANCO #");
            System.out.println("################");
            System.out.println("\n"
                    + "1 - Depositar.\n"
                    + "2 - Sacar.\n"
                    + "3 - Transferir.\n"
                    + "4 - Saldo.\n"
                    + "5 - Dados conta.\n"
                    + "6 - Encerrar.");
            switch (sc.nextInt()) {

                case 1:
                    System.out.println("###################");
                    System.out.println("# PAINEL DEPOSITO #");
                    System.out.println("###################");
                    System.out.println("Qual conta deseja depositar?");

                    System.out.println("\n"
                            + "1 - Conta corrente.\n"
                            + "2 - Conta poupança.");
                    switch (sc.nextInt()){
                    case 1:
                        System.out.println("Digite o valor a ser depositado: ");
                        cc.depositar(sc.nextDouble());
                        System.out.println("Digite 1 para continuar.");
                        continue;

                        case 2:
                        System.out.println("Digite o valor a ser depositado: ");
                        cp.depositar(sc.nextDouble());
                            System.out.println("Digite 1 para continuar.");

                            continue;

                        default:
                        System.out.println("Opção invalida!");
                            System.out.println("Digite 1 para continuar.");

                            continue;
            }
                case 2:
                    System.out.println("################");
                    System.out.println("# PAINEL SAQUE #");
                    System.out.println("################");
                    System.out.println("Qual conta deseja sacar?");
                    System.out.println("\n"
                            + "1 - Conta corrente.\n"
                            + "2 - Conta poupança.");
                    switch (sc.nextInt()) {
                        case 1:
                            System.out.println("Digite o valor a ser retirado: ");
                            cc.sacar(sc.nextDouble());
                            System.out.println("Digite 1 para continuar.");

                            continue;

                        case 2:
                            System.out.println("Digite o valor a ser retirado: ");
                            cp.sacar(sc.nextDouble());
                            System.out.println("Digite 1 para continuar.");

                            continue;

                        default:
                            System.out.println("Opção invalida!");
                            System.out.println("Digite 1 para continuar.");

                            continue;
                    }
                case 3:
                    System.out.println("#####################");
                    System.out.println("# PAINEL TRANSFERIR #");
                    System.out.println("#####################");
                    System.out.println("Para qual conta deseja transferir?");
                    System.out.println("\n"
                            + "1 - Conta corrente.\n"
                            + "2 - Conta poupança.");
                    switch (sc.nextInt()) {
                        case 1:
                            System.out.println("Digite o valor a ser transferido: ");
                            cc.transferir(sc.nextDouble(), cp);
                            System.out.println("Digite 1 para continuar.");

                            continue;

                        case 2:
                            System.out.println("Digite o valor a ser transferido: ");
                            cp.transferir(sc.nextDouble(), cc);
                            System.out.println("Digite 1 para continuar.");

                            continue;

                        default:
                            System.out.println("Opção invalida!");
                            System.out.println("Digite 1 para continuar.");

                            continue;
                    }
                case 4:
                    System.out.println("#########");
                    System.out.println("# SALDO #");
                    System.out.println("#########");

                    cc.imprimirSaldo();
                    System.out.println();
                    cp.imprimirSaldo();
                    System.out.println("Digite 1 para concluir.");

                break;

                case 5:
                    System.out.println("DADOS");
                    cc.imprimirConta();
                    System.out.println();
                    cp.imprimirConta();
                    System.out.println("Digite 1 para concluir.");


                break;
            }
        } while (sc.nextInt() != 6);
    }
    }
}
