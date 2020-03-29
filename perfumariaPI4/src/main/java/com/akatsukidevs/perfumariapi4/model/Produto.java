package com.akatsukidevs.perfumariapi4.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import com.sun.istack.NotNull;

@Entity
public class Produto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_produto;
	
	@NotEmpty
	private String nome_produto;
	@NotEmpty
	private String desc_produto;
	@NotNull
	private double preco;
	private boolean status=true;
	
	
	public Produto() {
		
	}	
	
	
	public Produto(Long id_produto, @NotEmpty String nome_produto, @NotEmpty String desc_produto,
			@NotEmpty double preco, boolean status) {
		super();
		this.id_produto = id_produto;
		this.nome_produto = nome_produto;
		this.desc_produto = desc_produto;
		this.preco = preco;
		this.status = status;
	}


	public Long getId_produto() {
		return id_produto;
	}


	public void setId_produto(Long id_produto) {
		this.id_produto = id_produto;
	}


	public String getNome_produto() {
		return nome_produto;
	}


	public void setNome_produto(String nome_produto) {
		this.nome_produto = nome_produto;
	}


	public String getDesc_produto() {
		return desc_produto;
	}


	public void setDesc_produto(String desc_produto) {
		this.desc_produto = desc_produto;
	}


	public double getPreco() {
		return preco;
	}


	public void setPreco(double preco) {
		this.preco = preco;
	}


	public boolean isStatus() {
		return status;
	}


	public void setStatus(boolean status) {
		this.status = status;
	}

	

}
