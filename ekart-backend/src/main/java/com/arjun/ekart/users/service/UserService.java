package com.arjun.ekart.users.service;

import java.time.LocalDateTime;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.arjun.ekart.cart.entity.Cart;
import com.arjun.ekart.cart.repository.CartRepository;
import com.arjun.ekart.users.dto.UserRegistrationRequest;
import com.arjun.ekart.users.entity.User;
import com.arjun.ekart.users.enums.Role;
import com.arjun.ekart.users.repository.UserRepository;
import com.arjun.ekart.wishlist.entity.Wishlist;
import com.arjun.ekart.wishlist.repository.WishlistRepository;



@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private WishlistRepository wishlistRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public String register(UserRegistrationRequest request) {
		// TODO Auto-generated method stub
		
		User user=new User();
		
		Cart cart=new Cart();
		
		Wishlist wishlist = new Wishlist();
		
		if (userRepository.findByEmail(request.getEmail()).isPresent()) {
		    return "User email already exists";
		}
		
		if(userRepository.findByMobile(request.getMobile()).isPresent()) {
			return "mobile number already taken";
		}

		if(userRepository.findByName(request.getName()).isPresent()) {
			return "username already taken";
		}

		
		user.setName(request.getName());
		user.setEmail(request.getEmail());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setMobile(request.getMobile());
		user.setRole(Role.USER);
		
		userRepository.save(user);
		
		cart.setCreatedAt(LocalDateTime.now());
		cart.setUpdatedAt(LocalDateTime.now());
		cart.setUser(user);
		
		cartRepository.save(cart);
		
		wishlist.setUser(user);
		
		
		wishlistRepository.save(wishlist);
		
		
		
		return "Registration Succesfull";
		
	}
	
	
}
