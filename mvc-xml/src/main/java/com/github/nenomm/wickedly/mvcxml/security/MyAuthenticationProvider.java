package com.github.nenomm.wickedly.mvcxml.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.NullAuthoritiesMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MyAuthenticationProvider implements AuthenticationProvider {
	@Autowired
	private UserDetailsService myUserDetailsService;
	private GrantedAuthoritiesMapper authoritiesMapper = new NullAuthoritiesMapper();

	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
		String username = token.getName();

		UserDetails user = myUserDetailsService.loadUserByUsername(username);

		Object principalToReturn = user;

		if (user == null) {
			// chatty insecure auth provider...
			throw new UsernameNotFoundException("User not found");
		}

		if (!user.getPassword().equalsIgnoreCase(authentication.getCredentials().toString())) {
			throw new BadCredentialsException("Bad credentials");
		}

		return createSuccessAuthentication(principalToReturn, authentication, user);
	}

	public boolean supports(Class<?> authentication) {
		return (UsernamePasswordAuthenticationToken.class
				.isAssignableFrom(authentication));
	}

	protected Authentication createSuccessAuthentication(Object principal,
			Authentication authentication, UserDetails user) {
		UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(
				principal, authentication.getCredentials(),
				authoritiesMapper.mapAuthorities(user.getAuthorities()));
		result.setDetails(authentication.getDetails());

		return result;
	}
}
