package com.akatsukidevs.perfumariapi4.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Endereco implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonIgnoreProperties(ignoreUnknown = true)
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String tipoend;
		
	private String rua;
		
	private String numero;
	
	private String complemento;
		
	private String bairro;
		
	private String cidade;
		
	private String estado;
		
	private String cep;
	
	private boolean status=true;
	
	@ManyToMany(mappedBy="enderecos")
	private Set<Pessoa> clientes = new HashSet<>();
	
	@OneToOne
	@JoinColumn(name="id_compra")
	private Compra compra;
	
	public Endereco() {
		
	}
	
	
	public Endereco(Long id, String tipoend, String rua, String numero, String complemento, String bairro,
			String cidade, String estado, String cep, boolean status, Set<Pessoa> clientes) {
		
		this.id = id;
		this.tipoend = tipoend;
		this.rua = rua;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
		this.cep = cep;
		this.status = status;
		this.clientes = clientes;
	}




	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	
	public String getTipoend() {
		return tipoend;
	}


	public void setTipoend(String tipoend) {
		this.tipoend = tipoend;
	}


	public String getRua() {
		return rua;
	}
	
	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	
	public String getBairro() {
		return bairro;
	}
	
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public String getCep() {
		return cep;
	}
	
	public void setCep(String cep) {
		this.cep = cep;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	

	public Set<Pessoa> getClientes() {
		return clientes;
	}


	public void setClientes(Set<Pessoa> clientes) {
		this.clientes = clientes;
	}
	
	


	public Compra getCompra() {
		return compra;
	}


	public void setCompra(Compra compra) {
		this.compra = compra;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Endereco other = (Endereco) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
