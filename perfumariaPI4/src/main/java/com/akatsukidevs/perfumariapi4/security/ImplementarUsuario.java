package com.akatsukidevs.perfumariapi4.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.akatsukidevs.perfumariapi4.model.Usuario;
import com.akatsukidevs.perfumariapi4.repository.UsuarioRepository;

@Repository
@Transactional
public class ImplementarUsuario implements UserDetailsService {
	
	@Autowired
	private UsuarioRepository ur;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario usuario = ur.findByEmail(email);
			
		if(usuario==null) {
			throw new UsernameNotFoundException("Usuario não encontrado");
		}
		
		return new User(usuario.getEmail(),usuario.getSenha(),usuario.isEnabled(), true, true,true,usuario.getAuthorities());
	}
	
	
}
