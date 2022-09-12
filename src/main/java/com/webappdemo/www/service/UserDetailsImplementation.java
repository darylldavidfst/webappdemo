package com.webappdemo.www.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.webappdemo.www.entity.User;
import com.webappdemo.www.repo.UserRepo;

@Service
@Transactional
public class UserDetailsImplementation implements UserDetailsService {
	private final UserRepo userRepo;

	public UserDetailsImplementation(UserRepo userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUsername(username);

		if (user == null) {
			throw new UsernameNotFoundException(username);
		}

		List<SimpleGrantedAuthority> authorities = user.getRoles()
				.stream()
				.map(role -> new SimpleGrantedAuthority(role.getName()))
				.collect(Collectors.toList());

		return new org.springframework.security.core.userdetails.User(
				user.getUsername(), user.getPassword(), authorities);
	}
}
