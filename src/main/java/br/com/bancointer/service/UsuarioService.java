package br.com.bancointer.service;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.Context;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bancointer.DTO.UsuarioDTO;
import br.com.bancointer.entity.Usuario;
import br.com.bancointer.mapper.CycleAvoidingMappingContext;
import br.com.bancointer.mapper.UsuarioMapper;
import br.com.bancointer.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	public UsuarioRepository repositorio;
	// @Autowired
	// UsuarioMapper map;

	public List<UsuarioDTO> pesquisarPorFiltro(Integer id, String name) {

		return null;
	}

	public List<UsuarioDTO> pesquisarTodos() {
		List<UsuarioDTO> usuarios = new ArrayList<UsuarioDTO>();
		repositorio.findAll().forEach(u ->{
			usuarios.add(UsuarioMapper.INSTANCE.usuarioToDto(u, new CycleAvoidingMappingContext()));
			
		});
		return usuarios;
	}

	public UsuarioDTO salvarUsuario(UsuarioDTO usuarioDto) {
		Usuario u = UsuarioMapper.INSTANCE.usuarioDtoToUsuario(usuarioDto, new CycleAvoidingMappingContext());

		u = repositorio.save(u);
		UsuarioDTO usuarioSalvo = UsuarioMapper.INSTANCE.usuarioToDto(u, new CycleAvoidingMappingContext());

		return usuarioSalvo;
	}

	public UsuarioDTO pesquisar(UsuarioDTO usuario) {
		if (usuario.getId() != null) {
			return UsuarioMapper.INSTANCE.usuarioToDto(repositorio.findById(usuario.getId()), new CycleAvoidingMappingContext());
		} else {
			return UsuarioMapper.INSTANCE.usuarioToDto(repositorio.findByEmail(usuario.getEmail()),new CycleAvoidingMappingContext());
		}

	}

	public void remover(Integer id) {
		repositorio.delete(id);		
	}

}
