package com.akatsukidevs.perfumariapi4.security;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.akatsukidevs.perfumariapi4.model.Usuario;
import com.akatsukidevs.perfumariapi4.repository.UsuarioRepository;

@Service
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
	
	
	private Map<String, User> roles = new HashMap<>();
	 
    @PostConstruct
    public void init() {
        roles.put("admin2", new User("admin", "{noop}admin1", getAuthority("ROLE_ADMIN")));
        roles.put("admin2", new User("admin", "{noop}admin1", getAuthority("ROLE_ESTOQUE")));
        roles.put("user2", new User("user", "{noop}user1", getAuthority("ROLE_COMPRADOR")));
    }
 
    private List<GrantedAuthority> getAuthority(String role) {
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }
   
	
}
