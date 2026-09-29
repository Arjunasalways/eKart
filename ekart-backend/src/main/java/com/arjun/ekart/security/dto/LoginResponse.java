package com.arjun.ekart.security.dto;

public class LoginResponse {
	
	private String accessToken;
    private long accessTokenExpiration;

	public LoginResponse(String accessToken, long accessTokenExpiration) {
		// TODO Auto-generated constructor stub
		this.accessToken = accessToken; 
        this.accessTokenExpiration = accessTokenExpiration;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public long getExpiresIn() {
		return accessTokenExpiration;
	}

	public void setExpiresIn(long accessTokenExpiration) {
		this.accessTokenExpiration = accessTokenExpiration;
	}
}
