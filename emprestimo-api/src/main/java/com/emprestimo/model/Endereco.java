package com.emprestimo.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;

@Embeddable
public class Endereco {
	@NotNull(message = "Logradouro é obrigatório")
	private String logradouro;
	@NotNull(message = "Número é obrigatório")
	private Long numero;
	@NotNull(message = "Bairro é obrigatório")
	private String bairro;
	@NotNull(message = "Cidade é obrigatório")
	private String cidade;
	@Enumerated(EnumType.STRING)
	@NotNull(message = "Estado é obrigatório")
	private Estado estado;
	
	public Endereco() {}
	
	public Endereco(String logradouro, Long numero, String bairro, String cidade, Estado estado) {
		super();
		this.logradouro = logradouro;
		this.numero = numero;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
}
