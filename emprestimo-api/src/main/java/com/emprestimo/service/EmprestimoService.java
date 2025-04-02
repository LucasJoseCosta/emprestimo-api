package com.emprestimo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

import com.emprestimo.dto.EmprestimoDTO;
import com.emprestimo.dto.EmprestimoPaginated;
import com.emprestimo.dto.EmprestimoSimuladoDTO;
import com.emprestimo.model.Emprestimo;

public interface EmprestimoService {
	public Optional<EmprestimoPaginated> findAllPaginated(Pageable pageable);
	public List<EmprestimoDTO> findAll();
	public Optional<EmprestimoDTO> findById(Long id);
	public EmprestimoDTO save(Emprestimo emprestimo);
	public Optional<EmprestimoDTO> update(Long id, Emprestimo emprestimo);
	public void delete(Long id);
	public Optional<EmprestimoSimuladoDTO> simularEmprestimo(EmprestimoSimuladoDTO emprestimo);
}
