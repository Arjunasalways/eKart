package com.arjun.ekart.users.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.arjun.ekart.users.dto.UserRegistrationRequest;
import com.arjun.ekart.users.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class UserController {

	@Autowired
	private UserService userservice;
	
	@PostMapping("/register")
	public String register(@Valid @RequestBody UserRegistrationRequest request) {
		
		
		return userservice.register(request);
				
	}
	
	@GetMapping
	public String Welcome(HttpServletRequest request) {
		
		return "welcome " +request.getSession().getId();
	}

	
	
}
