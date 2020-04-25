package com.akatsukidevs.perfumariapi4.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Entity
public class Usuario implements UserDetails, Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_usuario;
	
	@Email
	@Column(unique=true)
	@NotEmpty
	private String email;
	@NotEmpty
	private String senha;
	@NotEmpty
	private String tipo;
	private Boolean status=true;
	
	private transient List<SimpleGrantedAuthority> authorities;
	
	@OneToOne(mappedBy="usuario")
	@JoinColumn(name= "id_cliente")
	private Pessoa pessoa;
	
			
	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	
	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Usuario() {
		
			
	}
	
	public Usuario(Long id_usuario,@NotEmpty String email,@NotEmpty String senha,@NotEmpty String tipo) {
		BCryptPasswordEncoder bspe = new BCryptPasswordEncoder();
		this.id_usuario = id_usuario;
		this.email = email;
		this.senha =bspe.encode(senha);
		this.tipo = tipo;
		this.status=true;
		//this.authorities = Arrays.asList(new SimpleGrantedAuthority(this.tipo));
	}
	
	
	
	public Usuario(Long id_usuario, @Email @NotEmpty String email, @NotEmpty String senha, @NotEmpty String tipo,
			Boolean status, Pessoa pessoa) {
		super();
		this.id_usuario = id_usuario;
		this.email = email;
		this.senha = senha;
		this.tipo = tipo;
		this.status = status=true;
		this.pessoa = pessoa;
	}

	public Long getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(Long id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		BCryptPasswordEncoder bspe = new BCryptPasswordEncoder();
		this.senha = bspe.encode(senha);
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
		//this.authorities = Arrays.asList(new SimpleGrantedAuthority(this.tipo));
	}
	
	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
		
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id_usuario == null) ? 0 : id_usuario.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id_usuario == null) {
			if (other.id_usuario != null)
				return false;
		} else if (!id_usuario.equals(other.id_usuario))
			return false;
		return true;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.senha;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return this.status;
	}

	@Override
	public List<SimpleGrantedAuthority> getAuthorities() {
		authorities= Arrays.asList(new SimpleGrantedAuthority(this.tipo));
		return authorities;
	}
	

}