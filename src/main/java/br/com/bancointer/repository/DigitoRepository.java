package br.com.bancointer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.bancointer.entity.DigitoUnico;
import br.com.bancointer.entity.Usuario;

public interface DigitoRepository extends JpaRepository<DigitoUnico, Integer>{

	DigitoUnico findByDigitoEntrada(Integer digito);
	List<DigitoUnico> findByUsuario(Usuario usuario);
	
}
