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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.emprestimo.dto.UsuarioDTO;
import com.emprestimo.dto.UsuarioPaginated;
import com.emprestimo.errors.NotFoundException;
import com.emprestimo.model.Usuario;
import com.emprestimo.service.UsuarioServiceImp;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UsuarioController {
	@Autowired
	private UsuarioServiceImp usuarioService;
	
	@GetMapping
	public ResponseEntity<Object> findAllPaginated(@RequestParam(required = false) String searchTerm,@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
		if (pageable.getPageNumber() > 1) {
	        pageable = PageRequest.of(pageable.getPageNumber() - 1, pageable.getPageSize(), pageable.getSort());
	    } else {
	        Sort sort = pageable.getSort().isSorted() ? pageable.getSort() : Sort.by("id").ascending();
	        pageable = PageRequest.of(0, pageable.getPageSize(), sort);
	    }
		
		Optional<UsuarioPaginated> result = usuarioService.findAllPaginated(searchTerm,pageable);

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
		Optional<UsuarioDTO> usuarioOptional = usuarioService.findById(id);

		if (usuarioOptional.isPresent()) {
			return ResponseEntity.ok(usuarioOptional.get());
		} else {
			Map<String, Object> response = new HashMap<>();
			response.put("status", HttpStatus.NOT_FOUND.value());
			response.put("message", "Cliente não encontrado");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
	}
	
	@PostMapping
	public ResponseEntity<Object> createCliente(@Valid @RequestBody Usuario usuario) {
		try {
			UsuarioDTO novoUsuario = usuarioService.save(usuario);
			return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
		} catch (Exception e) {
			Map<String, Object> response = new HashMap<>();
			response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.put("message", "Erro ao criar usuario: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> updateCliente(@PathVariable Long id, @Valid @RequestBody Usuario usuario) {
		try {
			Optional<UsuarioDTO> updatedUsuarioOpt = usuarioService.update(id, usuario);

			if (updatedUsuarioOpt.isPresent()) {
				return ResponseEntity.ok(updatedUsuarioOpt.get());
			} else {
				Map<String, Object> response = new HashMap<>();
				response.put("status", HttpStatus.NOT_FOUND.value());
				response.put("message", "Usuario não encontrado para ID: " + id);
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
			response.put("message", "Erro ao atualizar o usuario: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteCliente(@PathVariable Long id) {
		try {
			usuarioService.delete(id);
			Map<String, Object> response = new HashMap<>();
			response.put("status", HttpStatus.OK.value());
			response.put("message", "Usuario deletado com sucesso");
			return ResponseEntity.ok(response);
		} catch (NotFoundException e) {
			Map<String, Object> response = new HashMap<>();
			response.put("status", HttpStatus.NOT_FOUND.value());
			response.put("message", e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		} catch (Exception e) {
			Map<String, Object> response = new HashMap<>();
			response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.put("message", "Erro ao deletar o usuario: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
}
