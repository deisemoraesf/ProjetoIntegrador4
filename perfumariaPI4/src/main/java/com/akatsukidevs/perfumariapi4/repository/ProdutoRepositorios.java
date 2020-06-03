package com.akatsukidevs.perfumariapi4.repository;


import org.springframework.data.repository.CrudRepository;

import com.akatsukidevs.perfumariapi4.model.Produto;


public interface ProdutoRepositorios extends CrudRepository<Produto, Long>{
	
	Iterable<Produto> findByStatus(boolean status);
	
	Iterable<Produto> findByCategoriaAndStatus(String categoria, boolean status);
	
	Iterable<Produto> findByNomeProdutoContainingIgnoreCaseAndStatus(String nome_produto, boolean status);
	
	//Iterable<Produto> findByNome_ProdutoContainingIgnoreCase(String nome);
	
	//@Query("SELECT p FROM Produto p WHERE lower(p.nome_produto) LIKE %:termo% OR lower(p.categoria) LIKE %:termo%")
	//List<Produto> searchJpql(@Param("termo") String termoBusca);

	//@Query(value = "SELECT * FROM contato WHERE lower(nome) = ?1 OR lower(apelido) = ?1", nativeQuery = true)
	//List<Produto> searchNativo(@Param("termo") String termoBusca);
}
