package com.arjun.ekart.security.config;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.arjun.ekart.security.jwt.JwtAuthenticationFilter;
import com.arjun.ekart.security.service.CustomUserDetailsService;



@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	
	@Bean
	public SecurityFilterChain SecurityFilerChain(HttpSecurity http) throws Exception {

	    http
	        .authorizeHttpRequests(auth -> auth
	        		
        		.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

	            // Public authentication endpoints
	            .requestMatchers(
	            	"/auth/register",
	                "/auth/login",
            	    "/auth/refresh"
	            ).permitAll()

	            // Public product images
	            .requestMatchers(
	            		HttpMethod.GET,
	            		"product-variants/**"
	            		).permitAll()
	            
	            // Public product browsing
	            .requestMatchers(
	                HttpMethod.GET,
	                "/products",
	                "/products/**"
	            ).permitAll()

	            // Public category browsing
	            .requestMatchers(
	            		HttpMethod.GET,
	            		"/categories",
	            		"/categories/**"
	            		).permitAll()
	            
	            //public product images
	            .requestMatchers(
	            		HttpMethod.GET,
	            		"/images",
	            		"/images/**"
	            		).permitAll()
	            

	            // Admin APIs
	            .requestMatchers("/admin/**")
	            .hasRole("ADMIN")

	            // User APIs
	            .requestMatchers("/users/**")
	            .hasRole("USER")
	            
	            


	            // Everything else requires authentication
	            .anyRequest()
	            .authenticated()
	        )

	        .csrf(csrf -> csrf.disable())

	        .cors(Customizer.withDefaults())

	        .formLogin(form -> form.disable())
	        
	        .httpBasic(basic -> basic.disable())

	        .addFilterBefore(
	            jwtAuthenticationFilter,
	            UsernamePasswordAuthenticationFilter.class
	        );

	    return http.build();
	}
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {

	    CorsConfiguration configuration = new CorsConfiguration();

	    configuration.setAllowedOrigins(
	        List.of("http://localhost:5173")
	    );

	    configuration.setAllowedMethods(
	        List.of("GET", "POST", "PUT", "DELETE", "OPTIONS")
	    );

	    configuration.setAllowedHeaders(
	        List.of("*")
	    );

	    configuration.setAllowCredentials(true);

	    UrlBasedCorsConfigurationSource source =
	        new UrlBasedCorsConfigurationSource();

	    source.registerCorsConfiguration("/**", configuration);

	    return source;
	}
	
	@Bean
	public AuthenticationManager authManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	@Bean 
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider(customUserDetailsService);
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}
		
	
	
}
