package com.emprestimo.controller;

import com.emprestimo.dto.EmprestimoDTO;
import com.emprestimo.dto.EmprestimoPaginated;
import com.emprestimo.dto.EmprestimoSimuladoDTO;
import com.emprestimo.errors.NotFoundException;
import com.emprestimo.model.Emprestimo;
import com.emprestimo.service.EmprestimoService;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {

    @Autowired
    private EmprestimoService emprestimoService;

    @GetMapping
    public ResponseEntity<EmprestimoPaginated> getAllEmprestimos(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC)Pageable pageable) {
    	if (pageable.getPageNumber() > 1) {
	        pageable = PageRequest.of(pageable.getPageNumber() - 1, pageable.getPageSize(), pageable.getSort());
	    } else {
	        Sort sort = pageable.getSort().isSorted() ? pageable.getSort() : Sort.by("id").ascending();
	        pageable = PageRequest.of(0, pageable.getPageSize(), sort);
	    }
    	
        return emprestimoService.findAllPaginated(pageable)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<EmprestimoDTO>> getAll() {
        List<EmprestimoDTO> emprestimos = emprestimoService.findAll();

        if (emprestimos.isEmpty()) {
            return ResponseEntity.noContent().build(); 
        }

        return ResponseEntity.ok(emprestimos); 
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmprestimoDTO> getEmprestimoById(@PathVariable Long id) {
        return emprestimoService.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new NotFoundException("Empréstimo não encontrado para o ID: " + id));
    }

    @PostMapping
    public ResponseEntity<EmprestimoDTO> createEmprestimo(@Valid @RequestBody Emprestimo emprestimo) {
        EmprestimoDTO savedEmprestimo = emprestimoService.save(emprestimo);
        return ResponseEntity.ok(savedEmprestimo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmprestimoDTO> updateEmprestimo(@PathVariable Long id, @Valid @RequestBody Emprestimo emprestimo) {
        return emprestimoService.update(id, emprestimo)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new NotFoundException("Empréstimo não encontrado para o ID: " + id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmprestimo(@PathVariable Long id) {
        emprestimoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/simular")
    public ResponseEntity<EmprestimoSimuladoDTO> simularEmprestimo(@Valid @RequestBody EmprestimoSimuladoDTO emprestimo) {
        return emprestimoService.simularEmprestimo(emprestimo)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new IllegalArgumentException("Erro ao simular empréstimo."));
    }
}
