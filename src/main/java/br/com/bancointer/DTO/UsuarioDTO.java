package br.com.bancointer.DTO;

import java.util.List;

public class UsuarioDTO {
	
	private Integer id;	
	private String nome;	
	private String email;
	List<DigitoUnicoDTO> digitos;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<DigitoUnicoDTO> getDigitos() {
		return digitos;
	}
	public void setDigitos(List<DigitoUnicoDTO> digitos) {
		this.digitos = digitos;
	}
	
	
}
