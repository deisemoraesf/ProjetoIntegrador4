package com.akatsukidevs.perfumariapi4.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.akatsukidevs.perfumariapi4.model.PessoaFisica;

public interface PessoaFisicaRepository extends JpaRepository <PessoaFisica, Long> {
	
	Iterable<PessoaFisica> findByStatus(boolean status);

}
