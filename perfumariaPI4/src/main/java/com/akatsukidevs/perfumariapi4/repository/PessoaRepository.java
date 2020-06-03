package com.akatsukidevs.perfumariapi4.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.akatsukidevs.perfumariapi4.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
	
	Iterable<Pessoa> findByStatus(boolean status);
	
	Iterable<Pessoa> findByNomeContainingIgnoreCaseAndStatus(String nome, boolean status);
	
	
}
