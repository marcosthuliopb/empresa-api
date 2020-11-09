package br.com.bancointer.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;
	@Column(name = "NOME")
	private String nome;
	@Column(name = "EMAIL")
	private String email;
	
	@OneToMany(mappedBy="usuario", cascade= {CascadeType.PERSIST,CascadeType.MERGE}, fetch=FetchType.LAZY)	
	List<DigitoUnico> digitos;

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

	public List<DigitoUnico> getDigitos() {
		return digitos;
	}

	public void setDigitos(List<DigitoUnico> digitos) {
		this.digitos = digitos;
	}

	

}
