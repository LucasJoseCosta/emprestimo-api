package com.emprestimo.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

@Entity
public class Emprestimo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@NotNull(message = "Cliente é obrigatório")
	private Cliente cliente;
	
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
	
	public Emprestimo() {}

	public Emprestimo(Long id, Cliente cliente, LocalDate dataEmprestimo, String moeda, BigDecimal valorObtido,
			BigDecimal taxaConversao, DatasVencimento dataVencimento, PeriodoParcelamento periodoParcelamento, BigDecimal valorPagamento) {
		super();
		this.id = id;
		this.cliente = cliente;
		this.dataEmprestimo = dataEmprestimo;
		this.moeda = moeda;
		this.valorObtido = valorObtido;
		this.taxaConversao = taxaConversao;
		this.dataVencimento = dataVencimento;
		this.periodoParcelamento = periodoParcelamento;
		this.valorPagamento = valorPagamento;
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
