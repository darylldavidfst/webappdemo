package com.webappdemo.www.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.webappdemo.www.entity.Role;
import com.webappdemo.www.entity.User;
import com.webappdemo.www.repo.RoleRepo;
import com.webappdemo.www.repo.UserRepo;

@Service
@Transactional
public class UserServiceImplementation implements UserService {

	private final UserRepo userRepo;
	private final RoleRepo roleRepo;
	private final PasswordEncoder passwordEncoder;

	public UserServiceImplementation(UserRepo userRepo, RoleRepo roleRepo, PasswordEncoder passwordEncoder) {
		this.userRepo = userRepo;
		this.roleRepo = roleRepo;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public User findByUsername(String username) {
		return userRepo.findByUsername(username);
	}

	@Override
	public User findById(int userId) {
		return userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User id "+userId+" not found"));
	}

	@Override
	public void saveRole(String name, String description) {
		roleRepo.save(new Role(name, description));
	}

	@Override
	public void saveUser(User rootUser) {
		rootUser.setPassword(passwordEncoder.encode(rootUser.getPassword()));
		userRepo.save(rootUser);
	}

	@Override
	public void addRoleToUser(String username, String roleName) {
		User user = userRepo.findByUsername(username);
		Role role = roleRepo.findByName(roleName);
		user.getRoles().add(role);
	}

	@Override
	public void deleteAll() {
		userRepo.deleteAll();
	}

	@Override
	public List<User> findAll() {
		return userRepo.findAll();
	}
}
