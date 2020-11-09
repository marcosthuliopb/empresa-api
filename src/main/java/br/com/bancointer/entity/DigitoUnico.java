package br.com.bancointer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class DigitoUnico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	Integer id;
	@Column(name = "DIGITO_ENTRADA")
	Integer digitoEntrada;
	@Column(name = "DIGITO_CALCULADO")
	Integer digitoCalculado;
	
	
	@ManyToOne	
	@JoinColumn(name="USUARIO_ID", referencedColumnName="ID")
	Usuario usuario;
	
	public Integer getId() {
		return id;
	}

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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((digitoCalculado == null) ? 0 : digitoCalculado.hashCode());
		result = prime * result + ((digitoEntrada == null) ? 0 : digitoEntrada.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DigitoUnico other = (DigitoUnico) obj;
		if (digitoCalculado == null) {
			if (other.digitoCalculado != null)
				return false;
		} else if (!digitoCalculado.equals(other.digitoCalculado))
			return false;
		if (digitoEntrada == null) {
			if (other.digitoEntrada != null)
				return false;
		} else if (!digitoEntrada.equals(other.digitoEntrada))
			return false;
		return true;
	}

	
	
	
	
}
