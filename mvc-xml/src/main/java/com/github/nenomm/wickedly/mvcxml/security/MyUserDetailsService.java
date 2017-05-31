package com.github.nenomm.wickedly.mvcxml.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.github.nenomm.wickedly.mvcxml.dao.BigDao;

@Service(value = "myUserDetailsService")
public class MyUserDetailsService implements UserDetailsService {
	@Autowired
	private BigDao bigDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MyUser user = bigDao.getUser(username);
		if (user != null) {
			user.setRoles(bigDao.getRolesForUser(user));
			return user.getSpringSecurityUser();
		}
		else {
			return null;
		}
	}
}
