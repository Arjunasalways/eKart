package com.arjun.ekart.security.dto;

import jakarta.validation.constraints.NotBlank;


public class UserLoginRequest {
	
	@NotBlank
	private String email;
	
	@NotBlank(message="message is required")
	private String password;
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}
		
	
}
