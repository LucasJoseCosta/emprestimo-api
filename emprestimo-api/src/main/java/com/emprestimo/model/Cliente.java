package com.emprestimo.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Entity
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "Nome é obrigatório")
	private String nome;
	@NotNull(message = "CPF é obrigatório")
	private String cpf;
	@NotNull(message = "Data de nascimento é obrigatório")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate dataNascimento;
	@Embedded
	@Valid
	@NotNull(message = "Endereço é obrigatório")
	private Endereco endereco;
	@NotNull(message = "E-mail é obrigatório")
	private String email;
	@NotNull(message = "Telefone é obrigatório")
	private String telefone;
	@Embedded
	@Valid
	@NotNull(message = "Dados bancarios são obrigatórios")
	private InfoBancarias infoBancarias;
	@Embedded
	private StatusCliente status;

	public Cliente() {
		this.status = new StatusCliente(Status.ATIVO, LocalDate.now());
	}
	
	public Cliente(Long id, String nome, String cpf, LocalDate dataNascimento, Endereco endereco, String email, String telefone,
			InfoBancarias infoBancarias, StatusCliente status) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.endereco = endereco;
		this.email = email;
		this.telefone = telefone;
		this.infoBancarias = infoBancarias;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public InfoBancarias getInfoBancarias() {
		return infoBancarias;
	}

	public void setInfoBancarias(InfoBancarias infoBancarias) {
		this.infoBancarias = infoBancarias;
	}

	public StatusCliente getStatus() {
		return status;
	}

	public void setStatus(StatusCliente status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", dataNascimento=" + dataNascimento
				+ ", endereco=" + endereco + ", email=" + email + ", telefone=" + telefone + ", infoBancarias="
				+ infoBancarias + ", status=" + status + "]";
	}
}
