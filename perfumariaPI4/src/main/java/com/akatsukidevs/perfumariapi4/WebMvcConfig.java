package com.akatsukidevs.perfumariapi4;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	
	@Autowired 
	private ServletContext context;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("**/acessofoto/**")
		//manter o file:/// toda vez que trocar a pasta, e reiniciar a Aplicação
		.addResourceLocations(context.getRealPath("/resources/static/acessofoto/"));		
		//.addResourceLocations("file:acessofoto/");
		//.addResourceLocations("file:///./images");
		//.addResourceLocations("file:///C:/Users/Fernanda Raeli/git/ProjetoIntegrador4/perfumariaPI4/src/main/resources/static/");
		//.addResourceLocations("file:///C:/Users/Deise/Documents/workspace-spring-tool-suite-4-4.5.1.RELEASE/perfumariaPI4/src/main/resources/static/");

	}

}
