package com.akatsukidevs.perfumariapi4.repository;

import org.springframework.data.repository.CrudRepository;

import com.akatsukidevs.perfumariapi4.model.Pessoa;

public interface PessoaRepository extends CrudRepository<Pessoa, Long> {
	
	Iterable<Pessoa> findByStatus(boolean status);
}
