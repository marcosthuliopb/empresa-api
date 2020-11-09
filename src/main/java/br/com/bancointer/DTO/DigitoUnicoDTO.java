package br.com.bancointer.DTO;

public class DigitoUnicoDTO {

	Integer id;	
	Integer digitoEntrada;	
	Integer digitoCalculado;	
		
	UsuarioDTO usuario;
	
	public Integer getDigitoEntrada() {
		return digitoEntrada;
	}
	public void setDigitoEntrada(Integer digitoEntrada) {
		this.digitoEntrada = digitoEntrada;
	}
	public Integer getDigitoCalculado() {
		return digitoCalculado;
	}
	public void setDigitoCalculado(Integer digitoCalculado) {
		this.digitoCalculado = digitoCalculado;
	}
	public UsuarioDTO getUsuario() {
		return usuario;
	}
	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	
}
