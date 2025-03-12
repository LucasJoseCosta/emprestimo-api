package com.emprestimo.model;

public enum Banco {
    BANCO_DO_BRASIL("Banco do Brasil"),
    CAIXA_ECONOMICA("Caixa Econômica Federal"),
    ITAU("Itaú"),
    BRADESCO("Bradesco"),
    SANTANDER("Santander"),
    BANCO_SAFRA("Banco Safra"),
    BANCO_INTER("Banco Inter"),
    BANCO_NUBANK("Nubank");

    private final String nome;

    Banco(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public static Banco getBancoPorNome(String nome) {
        for (Banco banco : values()) {
            if (banco.getNome().equalsIgnoreCase(nome)) {
                return banco;
            }
        }
        throw new IllegalArgumentException("Banco não encontrado: " + nome);
    }

    @Override
    public String toString() {
        return nome;
    }
}

