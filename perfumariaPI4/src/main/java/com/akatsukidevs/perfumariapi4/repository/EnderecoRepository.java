package com.akatsukidevs.perfumariapi4.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.akatsukidevs.perfumariapi4.model.Endereco;

public interface EnderecoRepository extends CrudRepository<Endereco, Long> {
	
	Endereco findByCep(String cep);
	Optional<Endereco> findById(Long id);
}
