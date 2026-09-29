package com.arjun.ekart.security.models;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.arjun.ekart.users.entity.User;

public class CustomUserDetails implements UserDetails{
	
	private final User user;
	
	
	
	public CustomUserDetails(User user) {
		super();
		this.user = user;
	}
	
	public Long getUserId() {
	    return user.getId();
	}


	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getEmail();
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
	}
}
