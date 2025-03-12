package com.emprestimo.model;

public enum DatasVencimento {
    DIA_5(5),
    DIA_10(10),
    DIA_15(15);

    private final int dia;

    DatasVencimento(int dia) {
        this.dia = dia;
    }

    public int getDia() {
        return dia;
    }
}

