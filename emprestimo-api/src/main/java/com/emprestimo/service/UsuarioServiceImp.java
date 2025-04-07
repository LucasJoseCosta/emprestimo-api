package com.emprestimo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.emprestimo.dto.UsuarioDTO;
import com.emprestimo.dto.UsuarioPaginated;
import com.emprestimo.errors.NotFoundException;
import com.emprestimo.model.Usuario;
import com.emprestimo.repository.UsuarioRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class UsuarioServiceImp implements UsuarioService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Optional<UsuarioPaginated> findAllPaginated(String searchTerm, Pageable pageable) {
		Page<Usuario> usuariosPage;

		if (searchTerm != null && !searchTerm.trim().isEmpty()) {
			String[] partes = searchTerm.trim().toLowerCase().split("\\s+");
			String parte1 = partes.length > 0 ? partes[0] : "";
			String parte2 = partes.length > 1 ? partes[1] : "";

			usuariosPage = usuarioRepository.buscarPorNomeFlexivel(parte1, parte2, pageable);
		} else {
			usuariosPage = usuarioRepository.findAll(pageable);
		}

		List<UsuarioDTO> usuariosDTO = usuariosPage.getContent().stream()
				.map(usuario -> modelMapper.map(usuario, UsuarioDTO.class)).collect(Collectors.toList());

		UsuarioPaginated paginatedResult = new UsuarioPaginated();
		paginatedResult.setContent(usuariosDTO);
		paginatedResult.setPageNumber(usuariosPage.getNumber());
		paginatedResult.setPageSize(usuariosPage.getSize());
		paginatedResult.setTotalElements(usuariosPage.getTotalElements());
		paginatedResult.setTotalPages(usuariosPage.getTotalPages());

		return Optional.of(paginatedResult);
	}

	@Override
	public Optional<UsuarioDTO> findById(Long id) {
		return usuarioRepository.findById(id).map(UsuarioDTO::new);
	}

	@Override
	public UsuarioDTO save(Usuario usuario) {
		Usuario savedUsuario = usuarioRepository.save(usuario);
		return new UsuarioDTO(savedUsuario);
	}

	@Override
	public Optional<UsuarioDTO> update(Long id, Usuario usuario) {
		Optional<Usuario> existingUsuarioOptional = usuarioRepository.findById(id);

		if (existingUsuarioOptional.isPresent()) {
			Usuario existingUsuario = existingUsuarioOptional.get();
			
			existingUsuario.setUsername(usuario.getUsername());
			existingUsuario.setPassword(usuario.getPassword());
			existingUsuario.setRole(usuario.getRole());
			existingUsuario.setFirst_name(usuario.getFirst_name());
			existingUsuario.setLast_name(usuario.getLast_name());

			Usuario updatedUsuario = usuarioRepository.save(existingUsuario);

			return Optional.of(new UsuarioDTO(updatedUsuario));
		} else {
			throw new NotFoundException("Usuario não encontrado para ID: " + id);
		}
	}

	@Override
	public void delete(Long id) {
		usuarioRepository.deleteById(id);
	}

}
