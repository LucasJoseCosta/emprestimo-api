package com.emprestimo.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.emprestimo.model.DatasVencimento;
import com.emprestimo.model.PeriodoParcelamento;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;

public class EmprestimoSimuladoDTO {
	@NotNull(message = "Data do emprestimo é obrigatório")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate dataEmprestimo;
	@NotNull(message = "Moeda é obrigatório")
	private String moeda;
	@NotNull(message = "Valor obtido é obrigatório")
	private BigDecimal valorObtido;
	@NotNull(message = "Taxa de conversão é obrigatório")
	private BigDecimal taxaConversao;
	
	@Enumerated(EnumType.STRING)
	@NotNull(message = "Data de vencimento é obrigatório")
    private DatasVencimento dataVencimento;

	@Enumerated(EnumType.STRING)
    @NotNull(message = "Periodo de parcelamento é obrigatório")
    private PeriodoParcelamento periodoParcelamento;
    
	private BigDecimal valorPagamento;
	
	public EmprestimoSimuladoDTO() {}

	public EmprestimoSimuladoDTO(LocalDate dataEmprestimo, String moeda, BigDecimal valorObtido,
			BigDecimal taxaConversao, DatasVencimento dataVencimento, PeriodoParcelamento periodoParcelamento,
			BigDecimal valorPagamento) {
		super();
		this.dataEmprestimo = dataEmprestimo;
		this.moeda = moeda;
		this.valorObtido = valorObtido;
		this.taxaConversao = taxaConversao;
		this.dataVencimento = dataVencimento;
		this.periodoParcelamento = periodoParcelamento;
		this.valorPagamento = valorPagamento;
	}

	public LocalDate getDataEmprestimo() {
		return dataEmprestimo;
	}

	public void setDataEmprestimo(LocalDate dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}

	public String getMoeda() {
		return moeda;
	}

	public void setMoeda(String moeda) {
		this.moeda = moeda;
	}

	public BigDecimal getValorObtido() {
		return valorObtido;
	}

	public void setValorObtido(BigDecimal valorObtido) {
		this.valorObtido = valorObtido;
	}

	public BigDecimal getTaxaConversao() {
		return taxaConversao;
	}

	public void setTaxaConversao(BigDecimal taxaConversao) {
		this.taxaConversao = taxaConversao;
	}

	public DatasVencimento getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(DatasVencimento dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public PeriodoParcelamento getPeriodoParcelamento() {
		return periodoParcelamento;
	}

	public void setPeriodoParcelamento(PeriodoParcelamento periodoParcelamento) {
		this.periodoParcelamento = periodoParcelamento;
	}

	public BigDecimal getValorPagamento() {
		return valorPagamento;
	}

	public void setValorPagamento(BigDecimal valorPagamento) {
		this.valorPagamento = valorPagamento;
	}
}
