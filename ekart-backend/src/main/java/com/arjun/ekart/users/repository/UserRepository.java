package com.arjun.ekart.users.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arjun.ekart.users.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{
	
	Optional<User> findByEmail(String email);
	Optional<User> findByMobile(String mobile);
	Optional<User> findByName(String name);
}
