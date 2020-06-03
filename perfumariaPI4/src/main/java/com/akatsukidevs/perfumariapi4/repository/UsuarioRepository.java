package com.akatsukidevs.perfumariapi4.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.akatsukidevs.perfumariapi4.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	
	Usuario findByEmail(String email);
	Iterable<Usuario> findByStatus(boolean status);
	
	
	Iterable<Usuario> findByEmailContainingIgnoreCaseAndStatus(String email, boolean status);	
		

}
