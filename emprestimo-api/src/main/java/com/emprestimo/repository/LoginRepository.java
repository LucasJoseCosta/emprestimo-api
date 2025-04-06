package com.emprestimo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emprestimo.model.Usuario;

@Repository
public interface LoginRepository extends JpaRepository<Usuario, Long>{
	public Optional<Usuario> findByUsername(String login);
}
