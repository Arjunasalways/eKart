package com.arjun.ekart.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.arjun.ekart.security.models.CustomUserDetails;
import com.arjun.ekart.users.entity.User;
import com.arjun.ekart.users.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
		@Autowired 
		private UserRepository userRepository;
	
		@Override
		public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
			User user = userRepository.findByEmail(email)
					.orElseThrow(()-> new RuntimeException("user not found"));
			
			return new CustomUserDetails(user);
		}
		
}
