package com.emprestimo.model;

public enum TipoConta {
	CORRENTE("Conta corrente"),
    POUPANÇA("Conta poupança");

    private final String nome;

    TipoConta(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public static TipoConta getTipoPorNome(String nome) {
        for (TipoConta tipoConta : values()) {
            if (tipoConta.getNome().equalsIgnoreCase(nome)) {
                return tipoConta;
            }
        }
        throw new IllegalArgumentException("Tipo de conta não encontrado: " + nome);
    }

    @Override
    public String toString() {
        return nome;
    }
}
