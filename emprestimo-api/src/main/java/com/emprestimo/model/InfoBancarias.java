package com.emprestimo.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;

@Embeddable
public class InfoBancarias {
	@Enumerated(EnumType.STRING)
	@NotNull(message = "Banco são obrigatórios")
	private Banco banco;
	@NotNull(message = "Agência são obrigatórios")
	private String agencia;
	@NotNull(message = "Dados bancarios são obrigatórios")
	private String contaBancaria;
	@Enumerated(EnumType.STRING)
	@NotNull(message = "Dados bancarios são obrigatórios")
	private TipoConta tipoConta;
	
	public InfoBancarias() {}
	
	public InfoBancarias(Banco banco, String agencia, String contaBancaria, TipoConta tipoConta) {
		super();
		this.banco = banco;
		this.agencia = agencia;
		this.contaBancaria = contaBancaria;
		this.tipoConta = tipoConta;
	}

	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getContaBancaria() {
		return contaBancaria;
	}

	public void setContaBancaria(String contaBancaria) {
		this.contaBancaria = contaBancaria;
	}

	public TipoConta getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(TipoConta tipoConta) {
		this.tipoConta = tipoConta;
	}
}
