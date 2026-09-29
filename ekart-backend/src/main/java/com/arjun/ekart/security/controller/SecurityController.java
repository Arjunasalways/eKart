package com.arjun.ekart.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arjun.ekart.security.dto.LoginResponse;
import com.arjun.ekart.security.dto.UserLoginRequest;
import com.arjun.ekart.security.service.AuthenticationService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class SecurityController {

    @Autowired
    private AuthenticationService authService;

    @PostMapping("/login")
    public LoginResponse login(
            @Valid @RequestBody UserLoginRequest request,
            HttpServletResponse response) {

        AuthenticationService.LoginResult result =
                authService.login(request);

        ResponseCookie refreshCookie =
                ResponseCookie.from(
                        "refreshToken",
                        result.refreshToken()
                )
                .httpOnly(true)
                .secure(false)       // localhost development
                .sameSite("Strict")
                .path("/auth")
                .maxAge(60L * 60L * 24L * 30L)
                .build();

        response.addHeader(
                "Set-Cookie",
                refreshCookie.toString()
        );

        return result.response();
    }
    
    @PostMapping("/refresh")
    public LoginResponse refresh(HttpServletRequest request) {

        Cookie[] cookies = request.getCookies();

        if (cookies == null) {
            throw new RuntimeException("Refresh token not found");
        }

        String refreshToken = null;

        for (Cookie cookie : cookies) {

            if ("refreshToken".equals(cookie.getName())) {
                refreshToken = cookie.getValue();
                break;
            }
        }

        if (refreshToken == null) {
            throw new RuntimeException("Refresh token not found");
        }

        return authService.refresh(refreshToken);
    }
}