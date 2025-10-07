package com.Auth.authenticationAndAuthorization.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.Auth.authenticationAndAuthorization.Security.JwtAuthenticationFilter;
import com.Auth.authenticationAndAuthorization.Security.JwtUtil;


@Configuration
public class SecurityConfig {
	
	private final JwtUtil jwtUtil;
    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(JwtUtil jwtUtil, CustomUserDetailsService  userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

	    @Bean
	    public JwtAuthenticationFilter jwtAuthenticationFilter() {
	        JwtAuthenticationFilter filter = new JwtAuthenticationFilter();
	        filter.setJwtUtil(jwtUtil);
	        filter.setUserDetailsService(userDetailsService);
	        return filter;
	    }

		@Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
			httpSecurity.cors().and().csrf().disable()
	            .authorizeHttpRequests(auth -> { 
	            	auth.requestMatchers("/")
	            	.permitAll()
	                .anyRequest()
	                .authenticated();
	            })
	            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	            .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

	        return httpSecurity.build();
	    }
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}

}
