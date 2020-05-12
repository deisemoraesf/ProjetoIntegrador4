package com.akatsukidevs.perfumariapi4;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/acessofoto/*")
		.addResourceLocations("file:///C:/Users/Deise/Documents/workspace-spring-tool-suite-4-4.5.1.RELEASE/perfumariaPI4/src/main/resources/static/");

	}

}
