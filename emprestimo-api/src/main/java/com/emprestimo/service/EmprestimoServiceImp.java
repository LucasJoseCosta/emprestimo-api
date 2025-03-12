package com.emprestimo.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.emprestimo.dto.EmprestimoDTO;
import com.emprestimo.dto.EmprestimoPaginated;
import com.emprestimo.dto.EmprestimoSimuladoDTO;
import com.emprestimo.errors.NotFoundException;
import com.emprestimo.model.Emprestimo;
import com.emprestimo.repository.EmprestimoRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class EmprestimoServiceImp implements EmprestimoService{
	@Autowired
	private EmprestimoRepository emprestimoRepository;
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Optional<EmprestimoPaginated> findAllPaginated(Pageable pageable) {
		Page<Emprestimo> emprestimosPage = emprestimoRepository.findAll(pageable);

		if (emprestimosPage.isEmpty()) {
			return Optional.empty();
		}

		EmprestimoPaginated response = new EmprestimoPaginated(
			emprestimosPage.getContent().stream().map(EmprestimoDTO::new).collect(Collectors.toList()),
			emprestimosPage.getNumber(),
			emprestimosPage.getSize(),
			emprestimosPage.getTotalElements(),
			emprestimosPage.getTotalPages()
		);

		return Optional.of(response);
	}

	@Override
	public Optional<EmprestimoDTO> findById(Long id) {
		return emprestimoRepository.findById(id).map(EmprestimoDTO::new);
	}

	@Override
	public EmprestimoDTO save(Emprestimo emprestimo) {
		Emprestimo savedEmprestimo = emprestimoRepository.save(emprestimo);
		return new EmprestimoDTO(savedEmprestimo);
	}

	@Override
	public Optional<EmprestimoDTO> update(Long id, Emprestimo emprestimo) {
		Optional<Emprestimo> existingEmprestimoOptional = emprestimoRepository.findById(id);

	    if (existingEmprestimoOptional.isPresent()) {
	    	Emprestimo existingEmprestimo = existingEmprestimoOptional.get();

	        existingEmprestimo.setCliente(emprestimo.getCliente());
	        existingEmprestimo.setDataEmprestimo(emprestimo.getDataEmprestimo());
	        existingEmprestimo.setMoeda(emprestimo.getMoeda());
	        existingEmprestimo.setValorObtido(emprestimo.getValorObtido());
	        existingEmprestimo.setTaxaConversao(emprestimo.getTaxaConversao());
	        existingEmprestimo.setDataVencimento(emprestimo.getDataVencimento());
	        existingEmprestimo.setPeriodoParcelamento(emprestimo.getPeriodoParcelamento());
	        existingEmprestimo.setValorPagamento(emprestimo.getValorPagamento());

	        Emprestimo updatedEmprestimo = emprestimoRepository.save(existingEmprestimo);

	        return Optional.of(new EmprestimoDTO(updatedEmprestimo));
	    } else {
	        throw new NotFoundException("Cliente não encontrado para ID: " + id);
	    }
	}

	@Override
	public void delete(Long id) {
		emprestimoRepository.deleteById(id);
	}

	@Override
	public Optional<EmprestimoSimuladoDTO> simularEmprestimo(EmprestimoSimuladoDTO request) {
	    LocalDate dataEmprestimo = request.getDataEmprestimo();

	    int meses = request.getPeriodoParcelamento().getMeses();
	    int diaVencimento = request.getDataVencimento().getDia();

	    LocalDate dataVencimentoCalculada = dataEmprestimo.plusMonths(meses);
	    dataVencimentoCalculada = dataVencimentoCalculada.withDayOfMonth(
	        Math.min(diaVencimento, dataVencimentoCalculada.lengthOfMonth())
	    );

	    BigDecimal valorConvertido = request.getValorObtido().multiply(request.getTaxaConversao());

	    final BigDecimal JUROS_MENSAL = new BigDecimal("0.02");
	    BigDecimal fator = BigDecimal.ONE.add(JUROS_MENSAL).pow(meses);
	    BigDecimal valorPagamento = valorConvertido.multiply(fator);

	    return Optional.of(new EmprestimoSimuladoDTO(
	        request.getDataEmprestimo(),
	        request.getMoeda(),
	        request.getValorObtido(),
	        request.getTaxaConversao(),
	        request.getDataVencimento(),
	        request.getPeriodoParcelamento(),
	        valorPagamento
	    ));
	}
	
}
