package com.akatsukidevs.perfumariapi4;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/acessofoto/**")
		//manter o file:/// toda vez que trocar a pasta, e reiniciar a Aplicação
		//Funciona para qualquer diretorio Local
		.addResourceLocations("file:///"+System.getProperty("user.dir")+"/src/main/resources/static/acessofoto/");		

	}

}
