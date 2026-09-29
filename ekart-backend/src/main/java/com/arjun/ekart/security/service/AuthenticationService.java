package com.arjun.ekart.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.arjun.ekart.security.dto.LoginResponse;
import com.arjun.ekart.security.dto.UserLoginRequest;
import com.arjun.ekart.security.jwt.JwtService;
import com.arjun.ekart.security.refresh.entity.RefreshToken;
import com.arjun.ekart.security.refresh.service.RefreshTokenService;
import com.arjun.ekart.users.entity.User;
import com.arjun.ekart.users.repository.UserRepository;

@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private UserDetailsService userDetailsService;

    @Value("${jwt.access-token-expiration}")
    private long accessTokenExpiration;

    public LoginResult login(UserLoginRequest request) {

        Authentication authentication = authManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.getEmail(),
                                request.getPassword()
                        )
                );

        UserDetails userDetails =
                (UserDetails) authentication.getPrincipal();

        // Generate short-lived access JWT
        String accessToken =
                jwtService.generateToken(userDetails);

        // Find the actual User entity
        User user =
                userRepository.findByEmail(request.getEmail())
                        .orElseThrow(
                                () -> new RuntimeException("User not found")
                        );

        // Generate refresh token
        String refreshToken =
                refreshTokenService.createRefreshToken(user);

        LoginResponse loginResponse =
                new LoginResponse(
                        accessToken,
                        accessTokenExpiration
                );

        return new LoginResult(
                loginResponse,
                refreshToken
        );
    }
    
    public LoginResponse refresh(String rawRefreshToken) {

        RefreshToken refreshToken =
                refreshTokenService.validateRefreshToken(rawRefreshToken);

        User user = refreshToken.getUser();

        UserDetails userDetails =
                userDetailsService.loadUserByUsername(user.getEmail());

        String newAccessToken =
                jwtService.generateToken(userDetails);

        return new LoginResponse(
                newAccessToken,
                accessTokenExpiration
        );
    }

    public record LoginResult(
            LoginResponse response,
            String refreshToken
    ) {
    }
}