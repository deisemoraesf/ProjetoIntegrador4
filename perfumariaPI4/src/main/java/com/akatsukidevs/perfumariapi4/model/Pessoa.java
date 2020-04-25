package com.akatsukidevs.perfumariapi4.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="tipoPessoa")
public class Pessoa implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id_pessoa;
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String nome2;
	
	@NotBlank
	private String celular;
	
	@NotBlank
	private String telefonefixo;
	
	
	private boolean status=true;
	
	@OneToOne(cascade=CascadeType.ALL)
	private Usuario usuario;
	
	@ManyToMany 
	@JoinTable(name = "tb_pessoa_endereco", joinColumns = @JoinColumn(name="id_pessoa"),
			   inverseJoinColumns = @JoinColumn(name="id_endereco"))
	private Set<Endereco> enderecos = new HashSet<>();
	
	
	public Pessoa() {
		
	}

	public Pessoa(Long id_pessoa, @NotBlank String nome, @NotBlank String nome2, @NotBlank String celular,
			@NotBlank String telefonefixo, boolean status) {
		this.id_pessoa = id_pessoa;
		this.nome = nome;
		this.nome2 = nome2;
		this.celular = celular;
		this.telefonefixo = telefonefixo;
		this.status = status=true;
	}
	
	
	public Pessoa(Long id_pessoa, @NotBlank String nome, @NotBlank String nome2, @NotBlank String celular,
			@NotBlank String telefonefixo, boolean status, Usuario usuario, Set<Endereco> enderecos) {
		super();
		this.id_pessoa = id_pessoa;
		this.nome = nome;
		this.nome2 = nome2;
		this.celular = celular;
		this.telefonefixo = telefonefixo;
		this.status = status;
		this.usuario = usuario;
		this.enderecos = enderecos;
	}

	public Long getId_pessoa() {
		return id_pessoa;
	}

	public void setId_pessoa(Long id_pessoa) {
		this.id_pessoa = id_pessoa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome2() {
		return nome2;
	}

	public void setNome2(String nome2) {
		this.nome2 = nome2;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getTelefonefixo() {
		return telefonefixo;
	}

	public void setTelefonefixo(String telefonefixo) {
		this.telefonefixo = telefonefixo;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Set<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(Set<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id_pessoa == null) ? 0 : id_pessoa.hashCode());
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
		Pessoa other = (Pessoa) obj;
		if (id_pessoa == null) {
			if (other.id_pessoa != null)
				return false;
		} else if (!id_pessoa.equals(other.id_pessoa))
			return false;
		return true;
	}
	
		
}
