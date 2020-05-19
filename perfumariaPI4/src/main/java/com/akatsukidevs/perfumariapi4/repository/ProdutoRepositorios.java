package com.akatsukidevs.perfumariapi4.repository;


import org.springframework.data.repository.CrudRepository;

import com.akatsukidevs.perfumariapi4.model.Produto;


public interface ProdutoRepositorios extends CrudRepository<Produto, Long>{
	
	Iterable<Produto> findByStatus(boolean status);
	
	//<Produto> findByNome_produtoContainingIgnoreCase(String nome);

	
}
