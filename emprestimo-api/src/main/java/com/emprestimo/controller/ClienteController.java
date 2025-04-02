package com.emprestimo.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.emprestimo.dto.ClienteDTO;
import com.emprestimo.dto.ClientePaginated;
import com.emprestimo.errors.NotFoundException;
import com.emprestimo.model.Cliente;
import com.emprestimo.service.ClienteServiceImp;

import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteServiceImp clienteServiceImp;

	@GetMapping
	public ResponseEntity<Object> findAllPaginated(@RequestParam(required = false) String searchTerm, @PageableDefault(page = 0) Pageable pageable) {
		if (pageable.getPageNumber() > 1) {
	        pageable = PageRequest.of(pageable.getPageNumber() - 1, pageable.getPageSize(), pageable.getSort());
	    } else {
	        pageable = PageRequest.of(0, pageable.getPageSize(), Sort.by("id").ascending());
	    }
		
		Optional<ClientePaginated> result = clienteServiceImp.findAllPaginated(searchTerm,pageable);

		if (result.isEmpty()) {
			Map<String, Object> response = new HashMap<>();
			response.put("status", HttpStatus.NOT_FOUND.value());
			response.put("message", "Sem registros");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}

		return ResponseEntity.ok(result.get());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> findById(@PathVariable Long id) {
		Optional<ClienteDTO> clienteOptional = clienteServiceImp.findById(id);

		if (clienteOptional.isPresent()) {
			return ResponseEntity.ok(clienteOptional.get());
		} else {
			Map<String, Object> response = new HashMap<>();
			response.put("status", HttpStatus.NOT_FOUND.value());
			response.put("message", "Cliente não encontrado");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
	}

	@PostMapping
	public ResponseEntity<Object> createCliente(@Valid @RequestBody Cliente cliente) {
		try {
			ClienteDTO novoCliente = clienteServiceImp.save(cliente);
			return ResponseEntity.status(HttpStatus.CREATED).body(novoCliente);
		} catch (Exception e) {
			Map<String, Object> response = new HashMap<>();
			response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.put("message", "Erro ao criar o cliente: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> updateCliente(@PathVariable Long id, @Valid @RequestBody Cliente cliente) {
		try {
			Optional<ClienteDTO> updatedClienteOpt = clienteServiceImp.update(id, cliente);

			if (updatedClienteOpt.isPresent()) {
				return ResponseEntity.ok(updatedClienteOpt.get());
			} else {
				Map<String, Object> response = new HashMap<>();
				response.put("status", HttpStatus.NOT_FOUND.value());
				response.put("message", "Cliente não encontrado para ID: " + id);
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
			}
		} catch (NotFoundException e) {
			Map<String, Object> response = new HashMap<>();
			response.put("status", HttpStatus.NOT_FOUND.value());
			response.put("message", e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		} catch (Exception e) {
			Map<String, Object> response = new HashMap<>();
			response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.put("message", "Erro ao atualizar o cliente: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteCliente(@PathVariable Long id) {
		try {
			clienteServiceImp.delete(id);
			Map<String, Object> response = new HashMap<>();
			response.put("status", HttpStatus.OK.value());
			response.put("message", "Cliente deletado com sucesso");
			return ResponseEntity.ok(response);
		} catch (NotFoundException e) {
			Map<String, Object> response = new HashMap<>();
			response.put("status", HttpStatus.NOT_FOUND.value());
			response.put("message", e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		} catch (Exception e) {
			Map<String, Object> response = new HashMap<>();
			response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.put("message", "Erro ao deletar o cliente: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
}
