package br.com.bancointer.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bancointer.DTO.DigitoUnicoDTO;
import br.com.bancointer.entity.DigitoUnico;
import br.com.bancointer.entity.Usuario;
import br.com.bancointer.mapper.CycleAvoidingMappingContext;
import br.com.bancointer.mapper.DigitoMapper;
import br.com.bancointer.repository.DigitoRepository;

@Service
public class DigitoService {

	@Autowired
	DigitoRepository repositorio;

	public Integer calcularDigitoUnico(Integer digito) {

		String digitoStr =String.valueOf(digito); 
		String[] arrayDigitos = digitoStr.split("");
		Integer soma = 0;
		for(String s: arrayDigitos) {
			soma+=Integer.valueOf(s);			
		}
		return soma;
	}
	
	public void salvarDigitoUnico(Integer digitoEntrada, Usuario usuario) {
		DigitoUnico digitoUnico = new DigitoUnico();
		digitoUnico.setUsuario(usuario);
		digitoUnico.setDigitoEntrada(digitoEntrada);
		digitoUnico.setDigitoCalculado(digitoEntrada);
		repositorio.save(digitoUnico);
		
	}

	public List<DigitoUnicoDTO> recuperarDigitosUsuario(Usuario usuario) {
		return repositorio.findByUsuario(usuario).stream().map(d -> {

			return DigitoMapper.INSTANCE.digitoUnicoToDto(d, new CycleAvoidingMappingContext());

		}).collect(Collectors.toList());
	}

}
