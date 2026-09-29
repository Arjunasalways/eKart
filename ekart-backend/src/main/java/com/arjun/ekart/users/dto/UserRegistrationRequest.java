package com.arjun.ekart.users.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserRegistrationRequest {
	
	@NotBlank
	private String name;
	
	@NotBlank(message="Email is required")
	@Email(message="Invalid Email Format")
	private String email;
	
	@Pattern(regexp = "^\\+91\\s[6-9]\\d{9}$")
	private String mobile;
	
	@NotBlank
	@Size(min=8, max=20,message="invalid password")
	@Pattern(
			regexp="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&]).*$",
			message="Password must contain at least one uppercase letter, one lowercase letter, "
					+ "one digit, and one special character.")
	private String password;
	
	
	public String getName() {
		return name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getMobile() {
		return mobile;
	}
	
	
	public String getPassword() {
		return password;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
