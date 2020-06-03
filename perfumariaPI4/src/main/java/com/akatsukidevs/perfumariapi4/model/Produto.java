package com.akatsukidevs.perfumariapi4.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import com.sun.istack.NotNull;



@Entity
public class Produto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_produto;
	
	@NotEmpty
	private String nomeProduto;
	
	@NotEmpty
	private String desc_produto;
	
	@NotNull
	private double preco;
	
	@NotNull
	private String categoria;
	
	private String perg_resp;
	
	private boolean status=true;
	
	@NotNull
	private int quantidade;
	
	
	@NotNull
	private String fotoIndex;
	
	
	@OneToMany(mappedBy = "produto", fetch = FetchType.LAZY)
	private Set<FotoProduto> imagens = new HashSet<>();
	
	
	public Long getId_produto() {
		return id_produto;
	}


	public void setId_produto(Long id_produto) {
		this.id_produto = id_produto;
	}

	
	public String getNomeProduto() {
		return nomeProduto;
	}


	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
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

	
	public String getCategoria() {
		return categoria;
	}



	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}


	public String getPerg_resp() {
		return perg_resp;
	}



	public void setPerg_resp(String perg_resp) {
		this.perg_resp = perg_resp;
	}


	public Set<FotoProduto> getImagens() {
		return imagens;
	}


	public void setImagens(Set<FotoProduto> imagens) {
		this.imagens = imagens;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public String getFotoIndex() {
		return fotoIndex;
	}

	public void setFotoIndex(String fotoIndex) {
		this.fotoIndex = fotoIndex;
	}
	
	
}
