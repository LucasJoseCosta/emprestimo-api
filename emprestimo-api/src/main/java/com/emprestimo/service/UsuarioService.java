package com.emprestimo.service;

import java.util.Optional;

import org.springframework.data.domain.Pageable;

import com.emprestimo.dto.UsuarioDTO;
import com.emprestimo.dto.UsuarioPaginated;
import com.emprestimo.model.Usuario;

public interface UsuarioService {
	public Optional<UsuarioPaginated> findAllPaginated(String searchTerm, Pageable pageable);
	public Optional<UsuarioDTO> findById(Long id);
	public UsuarioDTO save(Usuario usuario);
	public Optional<UsuarioDTO> update(Long id, Usuario usuario);
	public void delete(Long id);
}
