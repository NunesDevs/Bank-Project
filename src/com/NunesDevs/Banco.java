package com.NunesDevs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Banco {
    private String nome;
    private List<Conta> contas = new ArrayList<>();
    public List<Conta> getContas() { return Collections.unmodifiableList(new ArrayList<>(contas)); }
    public void setContas(List<Conta> contas) {
        if (contas == null) throw new IllegalArgumentException("Lista de contas obrigatória.");
        Set<String> numeros = new HashSet<>();
        for (Conta conta : contas) {
            if (conta == null || !numeros.add(conta.getNumero())) throw new IllegalArgumentException("Conta ausente ou duplicada.");
        }
        this.contas = new ArrayList<>(contas);
    }
    public String getNome() { return nome; }
    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) throw new IllegalArgumentException("Nome obrigatório.");
        this.nome = nome.trim();
    }
}
