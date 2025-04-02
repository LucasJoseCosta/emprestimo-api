package com.emprestimo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

import com.emprestimo.dto.ClienteDTO;
import com.emprestimo.dto.ClientePaginated;
import com.emprestimo.model.Cliente;

public interface ClienteService {
	public Optional<ClientePaginated> findAllPaginated(String searchTerm, Pageable pageable);
	public List<ClienteDTO> findAll();
	public Optional<ClienteDTO> findById(Long id);
	public ClienteDTO save(Cliente cliente);
	public Optional<ClienteDTO> update(Long id, Cliente cliente);
	public void delete(Long id);
}
