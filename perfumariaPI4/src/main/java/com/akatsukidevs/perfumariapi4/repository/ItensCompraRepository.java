package com.akatsukidevs.perfumariapi4.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.akatsukidevs.perfumariapi4.model.Compra;
import com.akatsukidevs.perfumariapi4.model.ItensCompra;

public interface ItensCompraRepository extends JpaRepository<ItensCompra, Long> {
		
	Iterable<ItensCompra> findByCompra(Compra compra);
}
