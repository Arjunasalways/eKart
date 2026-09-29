package com.arjun.ekart.security.jwt;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		
		String authHeader = request.getHeader("Authorization");
		
		System.out.println("================================");
		System.out.println("METHOD: " + request.getMethod());
		System.out.println("URI: " + request.getRequestURI());
		System.out.println("================================");
		
		
		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
		    filterChain.doFilter(request, response);
		    return;
		}
		
		String token = authHeader.substring(7);
		
		String username = jwtService.extractUserName(token);
		
		
		if(username != null && SecurityContextHolder.getContext().getAuthentication()==null) {

			UserDetails userDetails =
					userDetailsService.loadUserByUsername(username);
			
			System.out.println("USERNAME: " + username);
			System.out.println("AUTHORITIES: " + userDetails.getAuthorities());
		
			if(jwtService.isValid(userDetails,token)) {
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
											                            userDetails,
											                            null,
											                            userDetails.getAuthorities());
	
	            authentication.setDetails(new WebAuthenticationDetailsSource()
	                            .buildDetails(request));
	
	            SecurityContextHolder.getContext().setAuthentication(authentication);
			}
    }

    filterChain.doFilter(request, response);
    
	}

}
