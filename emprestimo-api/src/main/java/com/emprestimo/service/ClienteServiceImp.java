package com.emprestimo.service;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.emprestimo.dto.ClienteDTO;
import com.emprestimo.dto.ClientePaginated;
import com.emprestimo.errors.NotFoundException;
import com.emprestimo.model.Cliente;
import com.emprestimo.repository.ClienteRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class ClienteServiceImp implements ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Optional<ClientePaginated> findAllPaginated(Pageable pageable) {
		Page<Cliente> clientesPage = clienteRepository.findAll(pageable);

		if (clientesPage.isEmpty()) {
			return Optional.empty();
		}

		ClientePaginated response = new ClientePaginated(
			clientesPage.getContent().stream().map(ClienteDTO::new).collect(Collectors.toList()),
			clientesPage.getNumber(),
			clientesPage.getSize(),
			clientesPage.getTotalElements(),
			clientesPage.getTotalPages()
		);

		return Optional.of(response);
	}

	@Override
	public Optional<ClienteDTO> findById(Long id) {
		return clienteRepository.findById(id).map(ClienteDTO::new);
	}

	@Override
	public ClienteDTO save(Cliente cliente) {
		Cliente savedCliente = clienteRepository.save(cliente);
		return new ClienteDTO(savedCliente);
	}

	@Override
	public Optional<ClienteDTO> update(Long id, Cliente cliente) {
	    Optional<Cliente> existingClienteOptional = clienteRepository.findById(id);

	    if (existingClienteOptional.isPresent()) {
	        Cliente existingCliente = existingClienteOptional.get();

	        existingCliente.setNome(cliente.getNome());
	        existingCliente.setCpf(cliente.getCpf());
	        existingCliente.setDataNascimento(cliente.getDataNascimento());
	        existingCliente.setEndereco(cliente.getEndereco());
	        existingCliente.setEmail(cliente.getEmail());
	        existingCliente.setTelefone(cliente.getTelefone());
	        existingCliente.setInfoBancarias(cliente.getInfoBancarias());
	        existingCliente.setStatus(cliente.getStatus());

	        Cliente updatedCliente = clienteRepository.save(existingCliente);

	        return Optional.of(new ClienteDTO(updatedCliente));
	    } else {
	        throw new NotFoundException("Cliente não encontrado para ID: " + id);
	    }
	}

	@Override
	public void delete(Long id) {
		clienteRepository.deleteById(id);
	}
}
