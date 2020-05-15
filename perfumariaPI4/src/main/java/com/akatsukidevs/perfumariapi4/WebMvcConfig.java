package com.akatsukidevs.perfumariapi4;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/acessofoto/*")
		.addResourceLocations("file:///C:/Users/Igor/Documents/Trabalho Tsuda/Teste");

	}

}
