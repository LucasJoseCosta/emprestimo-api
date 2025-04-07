package com.emprestimo.dto;

import com.emprestimo.model.Usuario;

public class UsuarioDTO {

	private Long id;

	private String username;
	private String password;
	private String role;
	private String token;
	private String first_name;
	private String last_name;

	@SuppressWarnings("unused")
	private UsuarioDTO() {
	}

	public UsuarioDTO(Usuario usuario) {
		super();
		this.id = usuario.getId();
		this.username = usuario.getUsername();
		this.password = usuario.getPassword();
		this.role = usuario.getRole();
		this.token = usuario.getToken();
		this.first_name = usuario.getFirst_name();
		this.last_name = usuario.getLast_name();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

}
