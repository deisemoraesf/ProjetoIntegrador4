package com.akatsukidevs.perfumariapi4.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class ConfiguracaoSecurity extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private ImplementarUsuario userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
				// todas as paginas que tem"/" vão ser autenticados por todos
				// As restantes com hasRole eu identifico quem usar
				.antMatchers("/").permitAll()
				.antMatchers("/index").permitAll()
				.antMatchers("/categorias/**").permitAll()
				.antMatchers("/clientes/**").permitAll()
				.antMatchers("/acessofoto/**").permitAll()
				.antMatchers("/carrinho/**").permitAll()
				.antMatchers("/quemSomos").permitAll()
				.antMatchers("/finalizar/**").hasAnyRole("ADMIN", "ESTOQUE", "COMPRADOR")
				.antMatchers("/clientes/produtos/visualizarProdutos/**").permitAll()
				.antMatchers("https://viacep.com.br/**").permitAll()
				.antMatchers("/admin/").hasAnyRole("ADMIN", "ESTOQUE")
				.antMatchers("/indexLog").hasAnyRole("ADMIN", "ESTOQUE", "COMPRADOR")
				.antMatchers("/usuarios/**").hasAnyRole("ADMIN")
				.antMatchers("/clientesAdm/**").hasAnyRole("ADMIN")
				.antMatchers("/acessofoto/**").permitAll()
				.antMatchers("/produtos/**").hasAnyRole("ADMIN", "ESTOQUE")
				.anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll()
				.successHandler(myAuthenticationSuccessHandler())
				// se a pessoa quer sair só apertar "/logout"
				.and().logout()//.clearAuthentication(true)
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				//.logoutSuccessHandler(LogoutSucessHandler())
				.logoutSuccessUrl("/login?logout").permitAll()
				.and().rememberMe().userDetailsService(userDetailsService);
				
		
				//Caso entre em alguma pagina que não tenha permissão
				http.exceptionHandling().accessDeniedPage("/acessoNegado");
				
				//O usuário pode estar logado somente com 1 sessão
				http.sessionManagement()
                .maximumSessions(1)
                .maxSessionsPreventsLogin(false)
                .expiredUrl("/login?logout")
                .sessionRegistry(sessionRegistry());
       
	}
	
	@Bean
    SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }
	
	 
    @Bean
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler(){
        return new MySimpleUrlAuthenticationSuccessHandler();
    }
    
    @Bean
    public LogoutSuccessHandler LogoutSucessHandler(){
        return new MySimpleUrlAuthenticationSuccessHandler();
    }
    
    

	// autenticação com base em senha codificada
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService)
		.passwordEncoder(new BCryptPasswordEncoder()); //codificar a senha
		
	}

	// para não bloquear paginas estaticas, passa as pastas para o spring security ignorar
	@Override
	public void configure(WebSecurity WEB) throws Exception {
		WEB.ignoring().antMatchers("/css/**", "/image/**","/imgCarousel/**", "/Integrantes/**", "/acessofoto/**");
	}

}
