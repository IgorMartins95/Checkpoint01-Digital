package br.com.fiap.epictask.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.epictask.model.Usuarios;

public interface UsuariosRepository extends JpaRepository<Usuarios, Long>{

	Page<Usuarios> findByEmailLike(String email, Pageable pageable);

}
