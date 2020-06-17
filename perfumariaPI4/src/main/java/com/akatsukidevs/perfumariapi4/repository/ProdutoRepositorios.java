package com.akatsukidevs.perfumariapi4.repository;


import org.springframework.data.repository.CrudRepository;

import com.akatsukidevs.perfumariapi4.model.Produto;


public interface ProdutoRepositorios extends CrudRepository<Produto, Long>{
	
	Iterable<Produto> findByStatus(boolean status);
	
	Iterable<Produto> findByCategoriaAndStatus(String categoria, boolean status);
	
	Iterable<Produto> findByNomeProdutoContainingIgnoreCaseAndStatus(String nome_produto, boolean status);
	
}
