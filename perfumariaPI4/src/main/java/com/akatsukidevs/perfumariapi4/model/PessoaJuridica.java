package com.akatsukidevs.perfumariapi4.model;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

@Entity
@DiscriminatorValue("pj")
public class PessoaJuridica extends Pessoa implements Serializable{
	private static final long serialVersionUID = 1L;
	
		
	@NotBlank
	private String cnpj;
	private String inscricaoEstadual;
	private String inscricaoMunicipal;
	
	
	public PessoaJuridica(){
		
	}

	
	public PessoaJuridica(@NotBlank String cnpj, @NotBlank String inscricaoEstadual,
			@NotBlank String inscricaoMunicipal) {
		super();
		this.cnpj = cnpj;
		this.inscricaoEstadual = inscricaoEstadual;
		this.inscricaoMunicipal = inscricaoMunicipal;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}

	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}

	public String getInscricaoMunicipal() {
		return inscricaoMunicipal;
	}

	public void setInscricaoMunicipal(String inscricaoMunicipal) {
		this.inscricaoMunicipal = inscricaoMunicipal;
	}

		
}
