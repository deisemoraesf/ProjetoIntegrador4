package com.akatsukidevs.perfumariapi4.repository;

import org.springframework.data.repository.CrudRepository;

import com.akatsukidevs.perfumariapi4.model.PessoaFisica;

public interface PessoaFisicaRepository extends CrudRepository <PessoaFisica, Long> {
	
	Iterable<PessoaFisica> findByStatus(boolean status);

}
