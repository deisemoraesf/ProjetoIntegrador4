package com.akatsukidevs.perfumariapi4.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.akatsukidevs.perfumariapi4.model.FotoProduto;
import com.akatsukidevs.perfumariapi4.model.Produto;

public interface FotoProdutoRepository extends JpaRepository<FotoProduto, Long> {
	
	Iterable<FotoProduto> findByProduto(Produto p);
	Iterable<FotoProduto> findByStatus(boolean status);

}
