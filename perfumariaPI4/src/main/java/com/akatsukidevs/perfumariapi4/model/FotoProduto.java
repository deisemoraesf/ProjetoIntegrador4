package com.akatsukidevs.perfumariapi4.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

@Entity
public class FotoProduto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_foto;
	@NotEmpty
	private String url;
	
	@NotEmpty
	private String name;
	
	private boolean status=true;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_produto")
	private Produto produto;
	
	
	public FotoProduto() {
		
	}
	
	
	


	public FotoProduto(Long id_foto, @NotEmpty String url, @NotEmpty String name) {
		this.id_foto = id_foto;
		this.url = url;
		this.name = name;
		
	}




	public FotoProduto(Long id_foto, @NotEmpty String url, @NotEmpty String name, Produto produto) {
		this.id_foto = id_foto;
		this.url = url;
		this.name = name;
		this.produto = produto;
	}





	public Long getId_foto() {
		return id_foto;
	}

	public void setId_foto(Long id_foto) {
		this.id_foto = id_foto;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	
	

}
