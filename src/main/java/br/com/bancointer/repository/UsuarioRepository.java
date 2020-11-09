package br.com.bancointer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.bancointer.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

	Usuario findByEmail(String email);
	
	Usuario findById(Integer id);
	
	
}
