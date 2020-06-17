package com.akatsukidevs.perfumariapi4;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	
	@Autowired 
	private HttpServletRequest context;
	
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/acessofoto/**")
		//manter o file:/// toda vez que trocar a pasta, e reiniciar a Aplicação
		
		.addResourceLocations("file:///"+context.getServletContext().getRealPath("resources/static/acessofoto"));	
		//.addResourceLocations("file:acessofoto/");
		//.addResourceLocations("file:///./images");
		
		//Funciona para qualquer diretorio Local
		//.addResourceLocations("file:///"+System.getProperty("user.dir")+"/src/main/resources/static/acessofoto/");		

	}

}
