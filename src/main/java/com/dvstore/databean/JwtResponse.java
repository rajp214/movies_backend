package com.dvstore.databean;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;

public class JwtResponse {
	private String jwt;
	private String id;
	private String username;
	private String email;
	private List<String> authorities;
	
	public JwtResponse(String jwt, String id, String username, String email, List<String> authorities) {
		super();
		this.jwt = jwt;
		this.id = id;
		this.username = username;
		this.email = email;
		this.authorities = authorities;
	}
	public String getJwt() {
		return jwt;
	}
	public void setJwt(String jwt) {
		this.jwt = jwt;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<String> getAuthorities() {
		return authorities;
	}
	public void setAuthorities(List<String> authorities) {
		this.authorities = authorities;
	}
	
	
}
