package com.main.authserver.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.main.authserver.model.AuthUserDetails;
import com.main.authserver.model.User;
import com.main.authserver.repository.UserDetailsRepository;

import java.util.Optional;

@Service("userDetailService")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserDetailsRepository userDetailRepository;

	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

		Optional<User> optionalUser = userDetailRepository.findByUsername(name);

		optionalUser.orElseThrow(() -> new UsernameNotFoundException("Username or password wrong"));

		UserDetails userDetails = new AuthUserDetails(optionalUser.get());
		new AccountStatusUserDetailsChecker().check(userDetails);
		return userDetails;


	}
}
