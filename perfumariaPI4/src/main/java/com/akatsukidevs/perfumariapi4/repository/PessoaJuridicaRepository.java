package com.akatsukidevs.perfumariapi4.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.akatsukidevs.perfumariapi4.model.PessoaJuridica;

public interface PessoaJuridicaRepository extends JpaRepository<PessoaJuridica, Long> {
	
	Iterable<PessoaJuridica> findByStatus(boolean status);

}
