package com.akatsukidevs.perfumariapi4.repository;

import org.springframework.data.repository.CrudRepository;

import com.akatsukidevs.perfumariapi4.model.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long>{
	
	
	Usuario findByEmail(String email);
	Iterable<Usuario> findByStatus(boolean status);
	
	//@Query("select u from usuario u where u.status like 1 and u.email like %?1%")
	//Iterable<Usuario> findAllByEmail(String email);	
		

}
