package com.akatsukidevs.perfumariapi4.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Compra implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name="id_cliente")
	private Pessoa cliente;
	
	@OneToOne
	@JoinTable(name = "tb_compra_endereco", joinColumns = @JoinColumn(name="id_compra"),
	   inverseJoinColumns = @JoinColumn(name="id_endereco"))
	private Endereco endereco;
	
	@OneToMany
	@JoinColumn(name="id_items")
	private List<ItensCompra> itensCompra = new ArrayList<>();
	
	private String statusCompra;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCompra = new Date();
	private String formaPagamento;
	private Double valorTotal=0.;
	
	
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	
	public Pessoa getCliente() {
		return cliente;
	}
	public void setCliente(Pessoa cliente) {
		this.cliente = cliente;
	}
	public String getStatusCompra() {
		return statusCompra;
	}
	public void setStatusCompra(String statusCompra) {
		this.statusCompra = statusCompra;
	}
	public Date getDataCompra() {
		return dataCompra;
	}
	public void setDataCompra(Date dataCompra) {
		this.dataCompra = dataCompra;
	}
	public String getFormaPagamento() {
		return formaPagamento;
	}
	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	public Double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public List<ItensCompra> getItensCompra() {
		return itensCompra;
	}
	public void setItensCompra(List<ItensCompra> itensCompra) {
		this.itensCompra = itensCompra;
	}
	
	
}



