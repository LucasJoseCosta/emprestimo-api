package com.emprestimo.model;

public enum PeriodoParcelamento {
    MESES_6(6),
    MESES_12(12),
    MESES_18(18),
    MESES_24(24),
    MESES_30(30),
    MESES_36(36),
    MESES_42(42),
    MESES_48(48);

    private final int meses;

    PeriodoParcelamento(int meses) {
        this.meses = meses;
    }

    public int getMeses() {
        return meses;
    }
}
