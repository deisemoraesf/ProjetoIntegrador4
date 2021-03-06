package com.akatsukidevs.perfumariapi4.model;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

@Entity
@DiscriminatorValue("pf")
public class PessoaFisica extends Pessoa implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@NotBlank
	private String cpf;
	
	@NotBlank
	private String dtnascimento;
	
	@NotBlank
	private String genero;
	
	public PessoaFisica() {
		
	}
	
	
	public PessoaFisica(@NotBlank String cpf, @NotBlank String dtnascimento, @NotBlank String genero) {
		super();
		this.cpf = cpf;
		this.dtnascimento = dtnascimento;
		this.genero = genero;
	}


	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getDtnascimento() {
		return dtnascimento;
	}


	public void setDtnascimento(String dtnascimento) {
		this.dtnascimento = dtnascimento;
	}


	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	
		
}
