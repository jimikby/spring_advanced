package com.epam.theatre.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.epam.theatre.domain.Customer;
import com.google.common.collect.Sets;

@Service
public class AuthenticationService implements UserDetailsService {

	@Autowired
	private CustomerService customerService;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Customer customer = null;

		customer = customerService.takeByEmail(email);
		
	
	        Set<GrantedAuthority> authorities = Sets.newHashSet();

	        if (customer.getRole() != null) {
	        	customer.getRole().forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_" + role)));
	        }

		
		User user = new User(email, customer.getPassword(), true, true, true, true, authorities);
		
		UserDetails userDetails = user; 
		
		return userDetails;
	}

}