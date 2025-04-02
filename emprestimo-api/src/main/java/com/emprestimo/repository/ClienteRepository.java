package com.emprestimo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.emprestimo.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	@Query("SELECT c FROM Cliente c WHERE c.id = :id OR LOWER(c.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
    Page<Cliente> findByIdOrNomeContainingIgnoreCase(@Param("id") Long id, @Param("nome") String nome, Pageable pageable);

    Page<Cliente> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
}
