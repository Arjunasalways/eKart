package com.arjun.ekart.addresses.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arjun.ekart.addresses.entity.Address;
import com.arjun.ekart.users.entity.User;

public interface AddressRepository extends JpaRepository<Address,Long> {
	
	List<Address> findByUser(User user);
}
