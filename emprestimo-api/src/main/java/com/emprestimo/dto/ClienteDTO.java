package com.emprestimo.dto;

import java.time.LocalDate;

import com.emprestimo.model.Cliente;
import com.emprestimo.model.Endereco;
import com.emprestimo.model.InfoBancarias;
import com.emprestimo.model.StatusCliente;

public class ClienteDTO {
	
	private Long id;
	
	private String nome;
	private String cpf;
	private LocalDate dataNascimento;
	private Endereco endereco;
	private String email;
	private String telefone;
	private InfoBancarias infoBancarias;
	private StatusCliente status;
	
	public ClienteDTO() {}
	
	public ClienteDTO(Cliente cliente) {
		this.id = cliente.getId();
		this.nome = cliente.getNome();
		this.cpf = cliente.getCpf();
		this.dataNascimento = cliente.getDataNascimento();
		this.endereco = cliente.getEndereco();
		this.email = cliente.getEmail();
		this.telefone = cliente.getTelefone();
		this.infoBancarias = cliente.getInfoBancarias();
		this.status = cliente.getStatus();
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
}
