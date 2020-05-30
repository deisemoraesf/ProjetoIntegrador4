package com.akatsukidevs.perfumariapi4.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.akatsukidevs.perfumariapi4.model.Compra;
import com.akatsukidevs.perfumariapi4.model.Pessoa;

public interface CompraRepository extends JpaRepository<Compra, Long> {
	
	Iterable<Compra>findByCliente(Pessoa p);
	Optional<Compra> findById(Long id);
		
	
}
