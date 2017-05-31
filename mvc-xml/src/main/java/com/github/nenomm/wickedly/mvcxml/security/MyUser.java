package com.github.nenomm.wickedly.mvcxml.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class MyUser {
	private String username;
	private String password;
	private boolean enabled;
	private List<String> roles;

	public MyUser(String username, String password, boolean enabled) {
		this.username = username;
		this.password = password;
		this.enabled = enabled;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public User getSpringSecurityUser() {
		List<GrantedAuthority> auths = new ArrayList<>();

		for (String role : roles) {
			auths.add(new SimpleGrantedAuthority(role));
		}

		User user = new User(username, password, enabled, true, true, true, auths);

		return user;
	}
}
