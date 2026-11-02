package com.NunesDevs;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class Cliente {
    private String nome;
    private String cpf;
    private String data;
    public String getNome() { return nome; }
    public String getCpf() { return cpf; }
    public String getData() { return data; }
    public void setNome(String nome) {
        if (nome == null || nome.trim().length() < 2 || nome.trim().length() > 100)
            throw new IllegalArgumentException("Informe um nome entre 2 e 100 caracteres.");
        this.nome = nome.trim();
    }
    public void setCpf(String cpf) {
        if (cpf == null || !cpf.matches("[0-9.\\-]+")) throw new IllegalArgumentException("Informe um CPF com 11 dígitos.");
        String digits = cpf.replace(".", "").replace("-", "");
        if (!digits.matches("[0-9]{11}") || digits.matches("([0-9])\\1{10}")) throw new IllegalArgumentException("CPF inválido.");
        for (int length = 9; length <= 10; length++) {
            int sum = 0;
            for (int i = 0; i < length; i++) sum += (digits.charAt(i) - '0') * (length + 1 - i);
            int check = 11 - sum % 11;
            if ((check >= 10 ? 0 : check) != digits.charAt(length) - '0') throw new IllegalArgumentException("CPF inválido.");
        }
        this.cpf = digits;
    }
    public void setData(String data) {
        try {
            LocalDate date = LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT));
            if (date.isAfter(LocalDate.now()) || date.isBefore(LocalDate.now().minusYears(130)))
                throw new IllegalArgumentException("Data de nascimento fora do intervalo permitido.");
            this.data = data;
        } catch (DateTimeParseException | NullPointerException error) {
            throw new IllegalArgumentException("Use uma data válida no formato dd/MM/aaaa.");
        }
    }
}
