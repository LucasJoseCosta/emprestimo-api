package com.emprestimo.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.emprestimo.model.Cliente;
import com.emprestimo.model.DatasVencimento;
import com.emprestimo.model.Emprestimo;
import com.emprestimo.model.PeriodoParcelamento;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class EmprestimoDTO {

	private Long id;
	
	private Cliente cliente;

	private LocalDate dataEmprestimo;
	private String moeda;
	private BigDecimal valorObtido;
	private BigDecimal taxaConversao;

	@Enumerated(EnumType.STRING)
	private DatasVencimento dataVencimento;

	@Enumerated(EnumType.STRING)
	private PeriodoParcelamento periodoParcelamento;

	private BigDecimal valorPagamento;

	public EmprestimoDTO() {
	}

	public EmprestimoDTO(Emprestimo emprestimo) {
		super();
		this.id = emprestimo.getId();
		this.cliente = emprestimo.getCliente();
		this.dataEmprestimo = emprestimo.getDataEmprestimo();
		this.moeda = emprestimo.getMoeda();
		this.valorObtido = emprestimo.getValorObtido();
		this.taxaConversao = emprestimo.getTaxaConversao();
		this.dataVencimento = emprestimo.getDataVencimento();
		this.periodoParcelamento = emprestimo.getPeriodoParcelamento();
		this.valorPagamento = emprestimo.getValorPagamento();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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
