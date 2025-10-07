package com.Auth.authenticationAndAuthorization.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	@Override
	public void addCorsMappings(CorsRegistry corsRegistry) {
		corsRegistry.addMapping("/**")
		.allowedOriginPatterns("*")
		.allowedMethods("*")
		.allowedHeaders("*")
		.maxAge(3600)
		.exposedHeaders("Authorization")
		.allowCredentials(true);
	}

}
