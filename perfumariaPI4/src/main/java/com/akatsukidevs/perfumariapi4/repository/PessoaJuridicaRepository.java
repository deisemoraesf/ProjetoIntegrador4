package com.akatsukidevs.perfumariapi4.repository;

import org.springframework.data.repository.CrudRepository;

import com.akatsukidevs.perfumariapi4.model.PessoaJuridica;

public interface PessoaJuridicaRepository extends CrudRepository<PessoaJuridica, Long> {
	
	Iterable<PessoaJuridica> findByStatus(boolean status);

}
