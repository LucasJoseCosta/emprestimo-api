package com.emprestimo.model;

public enum Status {
	ATIVO("Ativo"),
    INADIMPLENTE("Inadimplente"),
    INATIVO("Inativo"),
    PENDENTE("Pendente");

    private final String nome;

    Status(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public static Status getTipoPorNome(String nome) {
        for (Status status : values()) {
            if (status.getNome().equalsIgnoreCase(nome)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Status não encontrado: " + nome);
    }

    @Override
    public String toString() {
        return nome;
    }
}
