package com.emprestimo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.emprestimo.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	@Query("""
			    SELECT u FROM Usuario u
			    WHERE (
			        LOWER(u.first_name) LIKE LOWER(CONCAT('%', :parte1, '%'))
			        AND LOWER(u.last_name) LIKE LOWER(CONCAT('%', :parte2, '%'))
			    )
			    OR (
			        LOWER(u.first_name) LIKE LOWER(CONCAT('%', :parte2, '%'))
			        AND LOWER(u.last_name) LIKE LOWER(CONCAT('%', :parte1, '%'))
			    )
			""")
	Page<Usuario> buscarPorNomeFlexivel(@Param("parte1") String parte1, @Param("parte2") String parte2,
			Pageable pageable);

}
