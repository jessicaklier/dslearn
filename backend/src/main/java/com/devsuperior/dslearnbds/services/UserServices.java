package com.devsuperior.dslearnbds.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.devsuperior.dslearnbds.entities.User;
import com.devsuperior.dslearnbds.repository.UserRepository;

@Service
public class UserServices implements UserDetailsService {
	
	private static Logger Logger = LoggerFactory.getLogger(UserServices.class);
	
	@Autowired
	private UserRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = repository.findByEmail(username);
		if (user == null) {
			Logger.error("User not found: + username");
			throw new UsernameNotFoundException("Email not found");
		}
		Logger.info("User found: " + username);
		return user;
	}
}
