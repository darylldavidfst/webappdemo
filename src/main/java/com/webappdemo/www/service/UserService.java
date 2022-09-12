package com.webappdemo.www.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.webappdemo.www.entity.User;

@Service
public interface UserService {

	User findByUsername(String username);

	User findById(int userId);

	void saveRole(String name, String description);

	void saveUser(User rootUser);

	void addRoleToUser(String username, String roleName);

	void deleteAll();

	List<User> findAll();
}
