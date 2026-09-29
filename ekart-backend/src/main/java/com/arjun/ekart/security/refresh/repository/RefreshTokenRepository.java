package com.arjun.ekart.security.refresh.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arjun.ekart.security.refresh.entity.RefreshToken;

public interface RefreshTokenRepository
        extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByTokenHash(String tokenHash);
    

}